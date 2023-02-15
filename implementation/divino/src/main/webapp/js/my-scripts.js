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
    }else{
             $("#input-credit-card").css("border-color", "#ced4da");
         }
    if (!patternExpire.test(expire)) {
        $("#input-card-expire").css("border-color", "red");
        flag = false
    }else{
             $("#input-card-expire").css("border-color", "#ced4da");
         }
    if (!patternCvc.test(cvc)) {
        $("#input-card-cvc").css("border-color", "red");
        flag = false
    }else{
             $("#input-card-cvc").css("border-color", "#ced4da");
         }
    let flag2 = validateAddress();
    if(flag==false || flag2 == false)
        return false
    else
        return true
}

function validateAddress() {
    let firstname = document.getElementById("input-firstname").value.trim();
    let lastName = document.getElementById("input-lastname").value.trim();
    let state = document.getElementById("input-state").value.trim();
    let address = document.getElementById("input-address").value.trim();
    let city = document.getElementById("input-address-city").value.trim();
    let prov = document.getElementById("input-address-prov").value.trim();
    let cap = document.getElementById("input-address-cap").value.trim();
    let cell = document.getElementById("input-phone").value.trim();

    let patternName = /^[a-zA-Z]{2,20}$/;
    let patternAddress = /^[a-zA-Z0-9\s]{2,20}$/
    let patternCAP = new RegExp("^[0-9]{5}$");
    let patternPhone = new RegExp("^[0-9]{10}$");
    let flag = true;

    if (!patternName.test(firstname)) {
        $("#input-firstname").css("border-color", "red");
        flag = false
    }else{
             $("#input-firstname").css("border-color", "#ced4da");
         }

    if (!patternName.test(lastName)) {
        $("#input-lastname").css("border-color", "red");
        flag = false
    }else{
             $("#input-lastname").css("border-color", "#ced4da");
         }


    if (!patternName.test(state)) {
        $("#input-state").css("border-color", "red");
        flag = false
    }else{
             $("#input-state").css("border-color", "#ced4da");
         }

    if (!patternAddress.test(address)) {
          $("#input-address").css("border-color", "red");
          flag = false
    }
    else{
            $("#input-address").css("border-color", "#ced4da");
        }

    if (!patternCAP.test(cap)) {
        $("#input-address-cap").css("border-color", "red");
        flag = false
    }else{
             $("#input-address-cap").css("border-color", "#ced4da");
         }

    if (!patternName.test(city)) {
        $("#input-address-city").css("border-color", "red");
        flag = false
    }
    else{
            $("#input-address-city").css("border-color", "#ced4da");
        }

    if (!patternName.test(prov)) {
          $("#input-address-prov").css("border-color", "red");
          flag = false
    }
    else{
            $("#input-address-prov").css("border-color", "#ced4da");
        }

    if (!patternPhone.test(cell)) {
          $("#input-phone").css("border-color", "red");
          flag = false
    }else{
             $("#input-phone").css("border-color", "#ced4da");
         }


    return flag
}

function validateUpdateAcc() {
    let email = document.getElementById("inputEmail").value.trim();
    let password = document.getElementById("inputPassword").value.trim();
    let firstname = document.getElementById("inputFirstname").value.trim();
    let lastName = document.getElementById("inputLastname").value.trim();

    let patternEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let patternPassword = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    let patternName = /^[a-zA-Z]{2,20}$/;
    let flag = true;

    if (!patternEmail.test(email)) {
        $("#inputEmail").css("border-color", "red");
        flag = false
    }else{
        $("#inputEmail").css("border-color", "#ced4da");
    }

    if (password!="" && !patternPassword.test(password)) {
        $("#inputPassword").css("border-color", "red");
        flag = false
    }else{
        $("#inputPassword").css("border-color", "#ced4da");
    }

    if (!patternName.test(firstname)) {
        $("#inputFirstname").css("border-color", "red");
        flag = false
    }else{
        $("#inputFirstname").css("border-color", "#ced4da");
    }

    if (!patternName.test(lastName)) {
        $("#inputLastname").css("border-color", "red");
        flag = false
    }else{
        $("#inputLastname").css("border-color", "#ced4da");
    }
    return flag
}

function validateProduct() {
    let brand = document.getElementById("inputBrand").value.trim();
    let description = document.getElementById("inputDescription").value.trim();
    let format = document.getElementById("inputModel").value.trim();
    let promo = document.getElementById("inputTitle").value.trim();
    let price = document.getElementById("inputPrice").value.trim();
    let pricePromo = document.getElementById("inputPriceSales").value.trim();
    let stock = document.getElementById("inputStock").value.trim();

    let patternName = /^[a-zA-Z\s]{2,20}$/;
    let patternPrice = /^[0-9]{1,6}$/;
    let flag = true;

    if (!patternName.test(brand)) {
        $("#inputBrand").css("border-color", "red");
        flag = false
    }else{
        $("#inputBrand").css("border-color", "#ced4da");
    }

    if (!patternName.test(description)) {
        $("#inputDescription").css("border-color", "red");
        flag = false
    }else{
        $("#inputDescription").css("border-color", "#ced4da");
    }

    if (!patternName.test(format)) {
        $("#inputModel").css("border-color", "red");
        flag = false
    }else{
        $("#inputModel").css("border-color", "#ced4da");
    }

    if (!patternPrice.test(price)) {
        $("#inputPrice").css("border-color", "red");
        flag = false
    }else{
        $("#inputPrice").css("border-color", "#ced4da");
    }

    if (!patternPrice.test(pricePromo)) {
        $("#inputPriceSales").css("border-color", "red");
        flag = false
    }else{
        $("#inputPriceSales").css("border-color", "#ced4da");
    }

    if (!patternPrice.test(stock)) {
        $("#inputStock").css("border-color", "red");
        flag = false
    }else{
        $("#inputStock").css("border-color", "#ced4da");
    }

    return flag
}

function validateSignup() {
    let email = document.getElementById("inputEmail").value.trim();
    let password = document.getElementById("inputPassword").value.trim();
    let firstname = document.getElementById("inputFirstname").value.trim();
    let lastName = document.getElementById("inputLastname").value.trim();

    let patternEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let patternPassword = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    let patternName = /^[a-zA-Z]{2,20}$/;
    let flag = true;

    if (!patternEmail.test(email)) {
        $("#inputEmail").css("border-color", "red");
        flag = false
    }else{
        $("#inputEmail").css("border-color", "#ced4da");
    }

    if (!patternPassword.test(password)) {
        $("#inputPassword").css("border-color", "red");
        flag = false
    }else{
        $("#inputPassword").css("border-color", "#ced4da");
    }

    if (!patternName.test(firstname)) {
        $("#inputFirstname").css("border-color", "red");
        flag = false
    }else{
        $("#inputFirstname").css("border-color", "#ced4da");
    }

    if (!patternName.test(lastName)) {
        $("#inputLastname").css("border-color", "red");
        flag = false
    }else{
        $("#inputLastname").css("border-color", "#ced4da");
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
    }else{
             $("#loginEmail").css("border-color", "#ced4da");
         }

    return flag
}




