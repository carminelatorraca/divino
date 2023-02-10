$(document).ready(
    function () {
        $(".zoom").hover(
            function (eventI) {
                $(eventI.target).css("transform", "scale(1.1)")
            },
            function (eventO) {
                $(eventO.target).css("transform", "scale(1.0)")

            }
        )
    }
)

function validatePayment() {
    let payment = document.getElementById("input-credit-card").value.trim();
    let expire = document.getElementById("input-card-expire").value.trim();
    let cvc = document.getElementById("input-card-cvc").value.trim();

    let patternPayment = new RegExp("^[0-9]{16}$");
    let patternExpire = new RegExp("^[0-9]{2}\\/[0-9]{2}$");
    let patternCvc = new RegExp("^[0-9]{3}$");

    let flag = true;

    if (!patternPayment.test(payment)) {
        $("#input-credit-card").css("border-color", "red");
        flag = false
    }
    if (!patternExpire.test(expire)) {
        $("#input-card-expire").css("border-color", "red");
        flag = false
    }
    if (!patternCvc.test(cvc)) {
        $("#input-card-cvc").css("border-color", "red");
        flag = false
    }
    return flag
}

function validateSignup() {
    let email = document.getElementById("inputEmail").value.trim();
    let password = document.getElementById("inputPassword").value.trim();

    let patternEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let patternPassword = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

    let flag = true;

    if (!patternEmail.test(email)) {
        $("#inputEmail").css("border-color", "red");
        flag = false
    }
    if (!patternPassword.test(password)) {
        $("#inputPassword").css("border-color", "red");
        flag = false
    }
    return flag
}

function validateSignIn() {
    let email = document.getElementById("loginEmail").value.trim();

    let patternEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    let flag = true;

    if (!patternEmail.test(email)) {
        $("#loginEmail").css("border-color", "red");
        flag = false
    }

    return flag
}


