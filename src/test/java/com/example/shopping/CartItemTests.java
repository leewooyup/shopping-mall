package com.example.shopping;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.PutInCartDto;
import com.example.shopping.service.cart.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartItemTests {
    @Autowired
    private CartService cartService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUpTestData() throws SQLException {
        jdbcTemplate.execute("TRUNCATE cart_item");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(5,9,nextval('cart_seq'),2), (12,1,nextval('cart_seq'),2), (13, 10, nextval('cart_seq'), 2), (14, 3, nextval('cart_seq'), 2), (15, 28, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(16,1,nextval('cart_seq'),2), (17,1,nextval('cart_seq'),2), (18, 12, nextval('cart_seq'), 2), (19, 2, nextval('cart_seq'), 2), (20, 5, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(21,4,nextval('cart_seq'),2), (22,1,nextval('cart_seq'),2), (23, 2, nextval('cart_seq'), 2), (24, 12, nextval('cart_seq'), 2), (25, 3, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(26,6,nextval('cart_seq'),2), (27,1,nextval('cart_seq'),2);");
    }

    @AfterEach
    public void tearDownTestData() throws SQLException {
        jdbcTemplate.execute("TRUNCATE cart_item");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(5,9,nextval('cart_seq'),2), (12,1,nextval('cart_seq'),2), (13, 10, nextval('cart_seq'), 2), (14, 3, nextval('cart_seq'), 2), (15, 28, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(16,1,nextval('cart_seq'),2), (17,1,nextval('cart_seq'),2), (18, 12, nextval('cart_seq'), 2), (19, 2, nextval('cart_seq'), 2), (20, 5, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(21,4,nextval('cart_seq'),2), (22,1,nextval('cart_seq'),2), (23, 2, nextval('cart_seq'), 2), (24, 12, nextval('cart_seq'), 2), (25, 3, nextval('cart_seq'), 2)");
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(26,6,nextval('cart_seq'),2), (27,1,nextval('cart_seq'),2);");
    }

    @Test
    @DisplayName("cart_id로 수량 변경")
    public void test_1() {
        long cartId = 3L;
        CartItem foundCartItem = cartService.showByCartId(cartId);
        long itemQuantity = foundCartItem.getItemQuantity();// 9L
        cartService.modifyItemQuantity(cartId, itemQuantity+2);
        CartItem updatedCartItem = cartService.showByCartId(cartId);
        assertEquals(11L, updatedCartItem.getItemQuantity());
    }

    @Test
    @DisplayName("cart_id로 장바구니 상품 삭제")
    public void test_2() {
        long cartId = 3L;
        long consumerId = 2L;
        cartService.removeByCartId(cartId);
        List<CartItem> cartItems = cartService.showByConsumerId(consumerId);
        assertEquals(16, cartItems.size());
    }

    @Test
    @DisplayName("PutInCartDto의 정보를 장바구니 상품에 추가")
    public void test_3() {
        long consumerId = 2L;
        PutInCartDto putInCartDto = new PutInCartDto(28L, 17L);
        cartService.register(putInCartDto, consumerId);
        List<CartItem> cartItems = cartService.showByConsumerId(consumerId);
        assertEquals(18, cartItems.size());
    }
}