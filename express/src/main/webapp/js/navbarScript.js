$(document).ready(function () {
  $('.dropdown-container').hide();

  $('.dropdown-megamenu').on('show.bs.dropdown', function () {
    $(this).find('.dropdown-container').first().stop(true, true).slideDown();
  });

  $('.dropdown-megamenu').on('hide.bs.dropdown', function () {
    $(this).find('.dropdown-container').first().stop(true, true).slideUp();
  });
});
