<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <div class="col-lg-8">
    <input id="input-err" type="hidden" value="${errMsg}" />
    <c:choose>
        <c:when test="${empty foundItemDtoAll}">
            <div id="empty-box" class="d-flex flex-column align-items-center">
                <div>
                    <i class="fa-solid fa-cart-shopping fa-2x" style="color:gray;"></i>
                </div>
                <div style="margin-top:20px;">
                    장바구니에 담긴 상품이 없어요.
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="shopping__cart__table">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${foundItemDtos}" var="cartItem">
                        <tr>
                            <c:choose>
                                <c:when test="${cartItem.isExcluded == true}">
                                    <td>
                                        <input type="checkbox" class="check-box check-box-${cartItem.itemId} mx-3" data-id="${cartItem.itemId}" />
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <input type="checkbox" class="check-box check-box-${cartItem.itemId} mx-3" data-id="${cartItem.itemId}" checked/>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                            <td class="product__cart__item">
                                <div class="product__cart__item__pic">
                                    <img src="${cartItem.itemImgPaths}" alt="">
                                </div>
                                <div class="product__cart__item__text">
                                    <h6>${cartItem.itemName}</h6>
                                    <h5><i class="fa-solid fa-won-sign"></i>  <fmt:formatNumber value="${cartItem.itemPrice}" /></h5>
                                </div>
                            </td>
                            <td class="quantity__item">
                                <div class="quantity d-flex flex-row">
                                    <i class="btn-decrease fa-solid fa-chevron-left" data-item="${cartItem.itemId}" data-id="${cartItem.cartId}" style="color:gray;padding-top:5px;"></i>
                                    <input type="text" value="${cartItem.itemQuantity}" data-id="${cartItem.cartId}" class="input-quantity input-quantity-${cartItem.itemId} mx-3" />
                                    <i class="btn-increase fa-solid fa-chevron-right" data-item="${cartItem.itemId}" data-id="${cartItem.cartId}" style="color:gray;padding-top:5px;"></i>
                                </div>
                            </td>
                            <td class="cart__price">
                                <div class="w-75" style="text-align:right;">
                                    <div><fmt:formatNumber value="${cartItem.subTotalPrice}" /></div>
                                </div>
                            </td>
                            <td><i class="btn-delete fa fa-close" data-id="${cartItem.cartId}"></i></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="container d-flex justify-content-center">
        <div class="paging">
            <input id="page-now" type="hidden" value="${pager.paging.pageNo}" />
            <input id="page-begin" type="hidden" value="${pager.beginPage}" />
            <input id="page-end" type="hidden" value="${pager.endPage}" />
            <ul class="d-flex flex-row">
                <c:if test="${pager.prev}">
                    <li style="list-style: none;"><a href="#" id="page-prev" class="btn btn-light">Prev</a></li>
                </c:if>
                <c:forEach var="no" begin="${pager.beginPage}" end="${pager.endPage}">
                    <c:choose>
                        <c:when test="${pager.paging.pageNo == no}">
                            <li style="list-style: none;" class="mx-1"><a href="#" class="page-no btn btn-light" data-page="${no}" style="color:red;">${no}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li style="list-style: none;" class="mx-1"><a href="#" class="page-no btn btn-light" data-page="${no}">${no}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pager.next}">
                    <li style="list-style: none;"><a href="#" id="page-next" class="btn btn-light">Next</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="row my-3">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="continue__btn">
                <a href="/home">Continue Shopping</a>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="cart__total">
        <h6>Cart total</h6>
        <ul>
            <c:set var="discountRate" value="0" /><!--hard coding.-->
            <c:set var="sum" value="0" />
            <c:set var="sumDiscount" value="0" />
            <c:forEach items="${foundItemDtoAll}" var="cartItem">
                <c:set var="discounted" value="${cartItem.subTotalPrice - (cartItem.subTotalPrice * discountRate)}" />
                <c:if test="${cartItem.isExcluded == false}">
                    <input class="checked-item" type="hidden" value="${cartItem.itemId}" />
                    <input class="checked-cart" type="hidden" value="${cartItem.cartId}" />
                    <input class="checked-quantity" type="hidden" value="${cartItem.itemQuantity}" />
                    <li class="checked-name" data-name="${cartItem.itemName}">${cartItem.itemName} <span style="color:#424242;font-weight:bolder;font-size:15px;" class="checked-discounted"><fmt:formatNumber value="${discounted}" pattern="#,##0" />원</span></li>
                    <c:set var="sumDiscount" value="${sumDiscount + (cartItem.subTotalPrice * discountRate)}" />
                    <c:set var="sum" value="${sum + discounted}" />
                </c:if>
            </c:forEach>
            <li>멤버십등급 <span style="color:#424242;font-weight:bolder;font-size:15px;">다이아 ( <fmt:formatNumber value="10" pattern="0.0" />% <i class="fa-solid fa-caret-down"></i> )</span></li>
            <li>상품할인금액 <span style="color:#424242;font-weight:bolder;font-size:15px;"> <fmt:formatNumber value="${sumDiscount}" pattern="#,##0" />원</span></li>
            <li>상품금액 <span style="color:#424242;font-weight:bolder;font-size:15px;"> <fmt:formatNumber value="${sum}" pattern="#,##0" />원</span></li>
        </ul>
        <form id="form-order" action="/order" method="post">
            <input type="text" name="orderItemDtoList" id="input-order"/>
            <button type="submit" class="primary-btn w-100">주문하기</button>
        </form>
    </div>
</div>
<script src="../../static/js/script.js"></script>
