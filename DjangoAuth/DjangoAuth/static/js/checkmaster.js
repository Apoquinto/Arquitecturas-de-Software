$(document).ready(function () {
    // Click on check-master enables/disables every checked box
    $("#check-master").click(function (event) {
        $(".checked-by-master").prop("checked", $("#check-master").prop("checked"));
    });

    // Not having every checked box enabled, disables the checkmaster, and the opposite.
    $(".checked-by-master").click(function (event) {
        var subCheckboxes = $(".checked-by-master");

        if (subCheckboxes.length == subCheckboxes.filter(":checked").length) {
            $("#check-master").prop("checked", true);
        } else {
            $("#check-master").prop("checked", false);
        }
    });

    // Method to post the selected elements
    $("#post-selected").submit(function (event) {
        event.preventDefault();
        var whereToPost = $(this).prop("action");
        var token = $("input[name='csrfmiddlewaretoken']").val();
        var ids = [];
        // Get checked id's
        $("#check-table tr").each(function (index) {
            if (index) {
                var rows = $(this);

                if (rows.find(".checked-by-master").prop("checked")) {
                    ids.push(rows.children(".e-id").text());
                }
            }
        });
        $.post(whereToPost, {csrfmiddlewaretoken: token, idCollection : ids});
        window.location.href = "../employees/";
    });
});