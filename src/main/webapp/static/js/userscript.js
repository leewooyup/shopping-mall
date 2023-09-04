$(function() {
    'use strict';

    //form switch.
    $('a.switch').on('click', function(event) {
        event.preventDefault();
        $(this).toggleClass('active');
        if($('a.switch').hasClass('active')) {
            $(this).parents('.form-peice').addClass('switched').siblings('.form-peice').removeClass('switched');
        } else {
            $(this).parents('.form-peice').removeClass('switched').siblings('.form-peice').addClass('switched');
        }
    });
});