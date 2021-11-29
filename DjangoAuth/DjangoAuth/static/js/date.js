$(document).ready(function () {
   var today = new Date();
   var dd = today.getDate();
   var mm = today.getMonth() + 1;
   var yyyy = today.getFullYear();

   if (dd < 10) {
      dd = '0' + dd;
   }

   if (mm < 10) {
      mm = '0' + mm;
   }

   today = yyyy + '-' + mm + '-' + dd;

   $('.min-date-today').attr("min", today);
});

function validateCorrectDates(startId, endId) {
   var isCorrect = false;
   var startDate = ($("#" + startId).val()).split("-");
   var endDate = ($("#" + endId).val()).split("-");

   // Year
   if (parseInt(startDate[0]) < parseInt(endDate[0])) {
      isCorrect = true;
   } else if (parseInt(startDate[0]) == parseInt(endDate[0])) {
      // Month
      if (parseInt(startDate[1]) < parseInt(endDate[1])) {
         isCorrect = true;
      } else if (parseInt(startDate[1]) == parseInt(endDate[1])) {
         // Day
         if (parseInt(startDate[2]) <= parseInt(endDate[2])) {
            isCorrect = true;
         } else {
            isCorrect = false;
         }
      } else {
         isCorrect = false;
      }
   } else {
      isCorrect = false;
   }

   if (!isCorrect) {
      alert("The dates have invalid values! Try again.");
   }

   return isCorrect;
}