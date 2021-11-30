// Error managers

$(".close-error-message").click(function (event) {
    $(event.currentTarget.dataset.errmsgId).toggleClass("is-hidden");
});

function showError(message) {
    $("#error-message-description").text(message);
    $("#error-message").removeClass("is-hidden");
}

// Validators

function validateEmail() {
    var emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    var isValid = emailRegex.test($("#email").val());

    if (!isValid) showError("Please insert a valid email.");

    return isValid;
}

function validateSamePasswords() {
    var passOriginal = $("#password1").val();
    var passCopy = $("#password2").val();
    var areEqual = passOriginal == passCopy;

    if (!areEqual) showError("Passwords don't match.");

    return areEqual;
}
