<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <div class="col-lg-8">
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
                            <i class="btn-decrease fa-solid fa-chevron-left" style="color:gray;padding-top:5px;"></i>
                            <input type="text" value="${cartItem.itemQuantity}" class="input-quantity mx-3" />
                            <i class="btn-increase fa-solid fa-chevron-right" style="color:gray;padding-top:5px;"></i>
                        </div>
                    </td>
                    <td class="cart__price">
                        <div class="w-75" style="text-align:right;">
                            <div><fmt:formatNumber value="${cartItem.subTotalPrice}" /></div>
                        </div>
                    </td>
                    <td><i class="btn-delete fa fa-close"></i></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="container d-flex justify-content-center">
        <div>
            <ul class="d-flex flex-row">
                <li style="list-style: none;"><a href="#" id="page-prev" class="btn btn-light">Prev</a></li>
                <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light">1</a></li>
                <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light">2</a></li>
                <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light">3</a></li>
                <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light">4</a></li>
                <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light">5</a></li>
                <li style="list-style: none;"><a href="#" id="page-next" class="btn btn-light">Next</a></li>
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
                <c:forEach items="${foundItemDtos}" var="cartItem">
                    <c:set var="discounted" value="${cartItem.subTotalPrice - (cartItem.subTotalPrice * discountRate)}" />
                    <c:if test="${cartItem.isExcluded == false}">
                        <li>${cartItem.itemName} <span style="color:#424242;font-weight:bolder;font-size:15px;"><fmt:formatNumber value="${discounted}" pattern="#,##0" />원</span></li>
                        <c:set var="sumDiscount" value="${sumDiscount + (cartItem.subTotalPrice * discountRate)}" />
                        <c:set var="sum" value="${sum + discounted}" />
                    </c:if>
                </c:forEach>
                <li>멤버십등급 <span style="color:#424242;font-weight:bolder;font-size:15px;">다이아 ( <fmt:formatNumber value="10" pattern="0.0" />% <i class="fa-solid fa-caret-down"></i> )</span></li>
                <li>상품할인금액 <span style="color:#424242;font-weight:bolder;font-size:15px;"> <fmt:formatNumber value="${sumDiscount}" pattern="#,##0" />원</span></li>
                <li>상품금액 <span style="color:#424242;font-weight:bolder;font-size:15px;"> <fmt:formatNumber value="${sum}" pattern="#,##0" />원</span></li>
            </ul>
        <form id="form-order" action="/order" method="post">
            <input type="hidden" name="orderItemDtoList" class="input-order"/>
            <button type="submit" class="primary-btn w-100">주문하기</button>
        </form>
    </div>
</div>
<script src="../../static/js/script.js"></script>
