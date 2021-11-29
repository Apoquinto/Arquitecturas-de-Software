function toggleModalClasses(event) {
    var modalId = event.currentTarget.dataset.modalId;
    var modal = $(modalId);
    modal.toggleClass('is-active');
    $('form').trigger('reset');
    $('html').toggleClass('is-clipped');
};

$('.open-modal').click(toggleModalClasses);
$('.close-modal').click(toggleModalClasses);