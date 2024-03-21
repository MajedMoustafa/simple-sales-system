package com.sales.quiz.service;

import com.sales.quiz.converter.OrderDTO;
import com.sales.quiz.converter.OrderMapper;
import com.sales.quiz.converter.OrderProductDTO;
import com.sales.quiz.exception.ResourceNotFoundException;
import com.sales.quiz.model.Client;
import com.sales.quiz.model.Order;
import com.sales.quiz.model.OrderProduct;
import com.sales.quiz.model.OrderProductPK;
import com.sales.quiz.model.OrderStatusEnum;
import com.sales.quiz.model.Product;
import com.sales.quiz.repository.ClientRepository;
import com.sales.quiz.repository.OrderProductRepository;
import com.sales.quiz.repository.OrderRepository;
import com.sales.quiz.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final ClientRepository clientRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ProductService productService, ClientRepository clientRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productService = productService;
        this.clientRepository = clientRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(OrderDTO order) {
        List<OrderProductDTO> formDtos = order.getOrderProducts();
        validateProductsExistence(formDtos);
        order.setDateCreated(LocalDate.now());
        order.setStatus(OrderStatusEnum.PAID.name());
        Order orderToSave = orderMapper.OrderDTOToOrder(order);
        Client client = clientRepository
                .findById(order.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        orderToSave.setClient(client);
        Order savedOrder = orderRepository.saveAndFlush(orderToSave);
        List<OrderProduct> orderProducts = new ArrayList<>();
        //create
        for (OrderProductDTO dto : formDtos) {
            OrderProduct orderProduct = new OrderProduct();
            OrderProductPK orderProductPK = new OrderProductPK();
            orderProductPK.setOrderId(savedOrder.getId());
            Product product =  productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderProductPK.setProduct(product);
            orderProduct.setPk(orderProductPK);
            orderProduct.setQuantity(dto.getQuantity());
            orderProducts.add(orderProduct);
        }
        orderProductRepository.saveAllAndFlush(orderProducts);
        savedOrder.setOrderProducts(orderProducts);
        return savedOrder;
    }


    private void validateProductsExistence(List<OrderProductDTO> orderProducts) {
        List<OrderProductDTO> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProductId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    @Override
    public Order update(OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Order orderToSave = orderMapper.OrderDTOToOrder(orderDTO);
        orderRepository.saveAndFlush(orderToSave);
        List<OrderProduct> orderProducts = new ArrayList<>();
        List<OrderProductDTO> formDtos = orderDTO.getOrderProducts();
        orderProductRepository.deleteAllByPkOrderId(orderDTO.getId());
        //create
        for (OrderProductDTO dto : formDtos) {
            OrderProduct orderProduct = new OrderProduct();
            OrderProductPK orderProductPK = new OrderProductPK();
            orderProductPK.setOrderId(orderToSave.getId());
            Product product =  productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderProductPK.setProduct(product);
            orderProduct.setPk(orderProductPK);
            orderProduct.setQuantity(dto.getQuantity());
            orderProducts.add(orderProduct);
        }
        orderProductRepository.saveAllAndFlush(orderProducts);
        orderToSave.setOrderProducts(orderProducts);
        return orderToSave;
    }

    @Override
    public Order getOrder(long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return order;
    }
}
