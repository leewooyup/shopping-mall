$(function() {
    //item quantity increase, decrease input
    $(".btn-decrease").on("click", function() {
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
            data: JSON.stringify({"cartId": cart, "itemQuantity": val}),
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
        let cur = $(this).data("item");
        let cart = $(this).data("id");
//        cart = parseInt(cart);
//        cur = parseInt(cur);
        let inputSec = ".input-quantity-" + cur;
        let val = parseInt($(inputSec).val());
        val = val + 1;
        $.LoadingOverlay("show");
        $.ajax({
            url: "/sm/c/api/update",
            type: "POST",
            data: JSON.stringify({"cartId": cart, "itemQuantity": val}),
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
    //checkbox toggle.
    $(".check-box").change(function() {
        let cur = $(this).data("id");
        let checkbox = ".check-box-" + cur;
        if(!$(checkbox).is(":checked")) { //체크 풀었을 때
            console.log("excluded: ", cur); //체크가 안된 itemId를 감지
            $.ajax({
                url: "/sm/c/api/v1",
                type: "POST",
                data: JSON.stringify({ "excludedItemId" : cur}),
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
                data: JSON.stringify({ "includedItemId" : cur}),
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
});