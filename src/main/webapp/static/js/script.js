toastr.options = {
    closeButton: false,
    debug: false,
    newestOnTop: false,
    progressBar: false,
    positionClass: "toast-top-right",
    preventDuplicates: false,
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

function successModal(msg) {
    toastr["success"](msg);
}

function errorModal(msg) {
    toastr["error"](msg);
}

$(function() {
    let errMsg = $("#input-err").val();
    if(errMsg != "") {
        console.log("errMsg call");
        errorModal(errMsg);
    }
    //pagination.
    $(".page-no").on("click", function() {
        let nowPage = $(this).data("page");
        $.ajax({
            url: "/sm/c/api/page",
            type: "POST",
            data: JSON.stringify({"nowPage": nowPage}),
            contentType: "application/json",
            success: function(result) {
                $("#std-parents").html(result);
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "(이)가 발생했습니다: " + status);
            }
        });
    });
    $("#page-next").on("click", function() {
        let endPage = parseInt($("#page-end").val());
        let nowPage = endPage + 1;
        $.ajax({
            url: "/sm/c/api/page",
            type: "POST",
            data: JSON.stringify({"nowPage": nowPage}),
            contentType: "application/json",
            success: function(result) {
                $("#std-parents").html(result);
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "(이)가 발생했습니다: " + status);
            }
        });
    });
    $("#page-prev").on("click", function() {
        let beginPage = parseInt($("#page-begin").val());
        let nowPage = beginPage - 1;
        $.ajax({
            url: "/sm/c/api/page",
            type: "POST",
            data: JSON.stringify({"nowPage": nowPage}),
            contentType: "application/json",
            success: function(result) {
                $("#std-parents").html(result);
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "(이)가 발생했습니다: " + status);
            }
        });
    });

    //x button click -> delete.
    $(".btn-delete").on("click", function() {
        let res = confirm("해당 상품을 장바구니에서 제거하시겠습니까?");
        if(res) {
            let cart = $(this).data("id");
            let nowPage = $("#page-now").val();
            $.LoadingOverlay("show");
            $.ajax({
                url: "/sm/c/api/delete",
                type: "POST",
                data: JSON.stringify({"nowPage": nowPage, "cartId": cart}),
                contentType: "application/json",
                success: function(result) {
                    $("#std-parents").html(result);
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err + "(이)가 발생했습니다: " + status);
                }
            });
            $.LoadingOverlay("hide");
        }
    });

    //item quantity increase, decrease input
    $(".btn-decrease").on("click", function() {
        let nowPage = parseInt($("#page-now").val());
        let cur = $(this).data("item");
        let cart = $(this).data("id");
        let inputSec = ".input-quantity-" + cur;
        let val = parseInt($(inputSec).val());
        if(val == 1) {
            val = 1;
        } else {
            val = val - 1;
        }
        $.LoadingOverlay("show");
        $.ajax({
            url: "sm/c/api/update",
            type: "POST",
            data: JSON.stringify({"nowPage": nowPage, "cartId": cart, "itemQuantity": val}),
            contentType: "application/json",
            success: function(result) {
                $("#std-parents").html(result);
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "(이)가 발생했습니다: " + status);
            }
        });
        $.LoadingOverlay("hide");
        $(inputSec).val(val);
    });
    $(".btn-increase").on("click", function() {
        let nowPage = parseInt($("#page-now").val());
        let cur = $(this).data("item");
        let cart = $(this).data("id");
        let inputSec = ".input-quantity-" + cur;
        let val = parseInt($(inputSec).val());
        val = val + 1;
        $.LoadingOverlay("show");
        $.ajax({
            url: "/sm/c/api/update",
            type: "POST",
            data: JSON.stringify({"nowPage": nowPage, "cartId": cart, "itemQuantity": val}),
            contentType: "application/json",
            success: function(result) {
                $("#std-parents").html(result);
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "(이)가 발생했습니다: " + status);
            }
        });
        $.LoadingOverlay("hide");
        $(inputSec).val(val);
    });
    $(".input-quantity").keypress(function(event) {
        let nowPage = parseInt($("#page-now").val());
        if(event.which === 13) { //Enter 키의 key code는 13.
            let cart = $(this).data("id");
            let val = parseInt($(this).val());
            if(val > 999) {
                val = 999;
            } else if(val < 1) {
                val = 1;
            }
            $.LoadingOverlay("show");
            $.ajax({
                url: "/sm/c/api/update",
                type: "POST",
                data: JSON.stringify({"nowPage": nowPage, "cartId": cart, "itemQuantity" : val}),
                contentType: "application/json",
                success: function(result) {
                    $("#std-parents").html(result);
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err +"(이)가 발생했습니다: " + status);
                }
            });
            $.LoadingOverlay("hide");
            $(this).val(val);
        }
    });

    //checkbox toggle.
    $(".check-box").change(function() {
        let nowPage = parseInt($("#page-now").val());
        let cur = $(this).data("id");
        let checkbox = ".check-box-" + cur;
        if(!$(checkbox).is(":checked")) { //체크 풀었을 때
            console.log("excluded: ", cur); //체크가 안된 itemId를 감지
            $.ajax({
                url: "/sm/c/api/v1",
                type: "POST",
                data: JSON.stringify({ "nowPage": nowPage, "excludedItemId" : cur}),
                contentType: "application/json",
                success: function(result) {
                    $("#std-parents").html(result);
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err + "이(가) 발생했습니다: " + status);
                }
            });
        } else { //체크했을 때
            $.ajax({
                url: "/sm/c/api/v2",
                type: "POST",
                data: JSON.stringify({ "nowPage": nowPage, "includedItemId" : cur}),
                contentType: "application/json",
                success: function(result) {
                    $("#std-parents").html(result);
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err + "이(가) 발생했습니다: " + status);
                }
            });
        }
    });

    //order button click.
    $("#form-order").submit(function(event){
        event.preventDefault();
        let itemIdArr = [];
        let itemNameArr = [];
        let itemQuantityArr = [];
        let eachDiscountedArr = [];
        let cartIdArr = [];

        $(".checked-item").each(function(){
            let itemId = $(this).val();
            itemIdArr.push(itemId);
        });

        $(".checked-name").each(function() {
            let itemName = $(this).data("name");
            itemNameArr.push(itemName);
        });

        $(".checked-quantity").each(function() {
            let itemQuantity = $(this).val();
            itemQuantityArr.push(itemQuantity);
        });

        $(".checked-discounted").each(function(){
            let eachDiscounted = $(this).text();
            eachDiscounted = eachDiscounted.replace(/,/g, '');
            eachDiscounted = eachDiscounted.replace(/원/g, '');
            eachDiscountedArr.push(eachDiscounted);
        });

        $(".checked-cart").each(function() {
            let cartId = $(this).val();
            cartIdArr.push(cartId);
        });

        let datas = [];
        for(let i = 0; i < itemIdArr.length; i++) {
            let jsonFormat = {};
            jsonFormat["itemId"] = itemIdArr[i];
            jsonFormat["itemName"] = itemNameArr[i];
            jsonFormat["itemQuantity"] = itemQuantityArr[i];
            jsonFormat["itemPrice"] = eachDiscountedArr[i];
            jsonFormat["cartId"] = cartIdArr[i];
            datas.push(jsonFormat);
        }
        //test console log.
        for(let i = 0; i < datas.length; i++) {
            let jsonData = JSON.stringify(datas[i]);
            console.log("each: " + jsonData);
        }
        let jsonData = JSON.stringify(datas);
        $("#input-order").val(datas);
        let val = String($("#input-order").val()).trim();
        if(val == "") {
            alert("장바구니 상품을 1개 이상 선택해주세요.");
            return false;
        }
    });
});