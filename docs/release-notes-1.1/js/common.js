//Disable user notification
function showNotificationBox() {
    setTimeout(function () {
        $('.notification-box').removeClass('d-none').addClass('animated slideInDown');
    }, 1000)
}

function removeNotificationBox() {
    $('.notification-box').removeClass('slideInDown').addClass('animated fadeOutUp');
}

// Show hide left menu
function hideLeftMenu() {
    $('.sidebar').addClass('minimized');
    $('.sidebar-header').addClass('minimized');
    $('.sidebar-footer').addClass('minimized');
    $('.sub-header').addClass('minimized');
    $('.flex-shrink-0').addClass('without-left-menu');
}

function showLeftMenu() {
    $('.sidebar').removeClass('minimized');
    $('.sidebar-header').removeClass('minimized');
    $('.sidebar-footer').removeClass('minimized');
    $('.sub-header').removeClass('minimized');
    $('.flex-shrink-0').removeClass('without-left-menu');
}

// Enable dark theme
$('.enable-dark-theme-switch input:checkbox').change(function(){
    if($(this).is(":checked")) {
        $('body').addClass("dark-theme");
    } else {
        $('body').removeClass("dark-theme");
    }
});

// Open notification panel
function openNotificationPanel() {
    $('#notification-right-popup').removeClass('right-panel__closed');
}

function closeNotificationPanel() {
    $('#notification-right-popup').addClass('right-panel__closed');
}

// Bottom action bar
function showBottomActionBar() {
    $('.bottom-action-bar').removeClass('d-none').addClass('animated slideInUp');
}

function removeBottomActionBar() {
    $('.bottom-action-bar').removeClass('slideInUp').addClass('animated fadeOutDown');
}