$(function() {
  // Sidebar toggle behavior
  $('#sidebarCollapse').on('click', function() {
    $('#sidebar, #content').toggleClass('active');
  });
	$('#sidenav-icon').click(function(){
		$(this).toggleClass('open');
	});
});
