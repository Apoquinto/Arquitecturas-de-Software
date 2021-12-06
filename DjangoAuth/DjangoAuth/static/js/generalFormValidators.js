// Error managers

$(".close-error-message").click(function (event) {
  $(event.currentTarget.dataset.errmsgId).toggleClass("is-hidden");
});

function showError(message) {
  $("#error-message-description").text(message);
  $("#error-message").removeClass("is-hidden");
}

// Validators
function validateSamePasswords() {
  var passOriginal = $("#password1").val();
  var passCopy = $("#password2").val();
  var areEqual = passOriginal == passCopy;

  if (!areEqual) showError("Passwords don't match.");

  return areEqual;
}
