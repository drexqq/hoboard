$(window).on("load resize", function () {
  const listHeight = $(".list-wrap .list").outerHeight(true);
  const enrollListWrap = $(".map-wrap .side-list .enroll-wrap .list-wrap");
  const apiListWrap = $(".map-wrap .side-list .api-wrap .list-wrap");
  enrollListWrap.css("max-height", 2 * listHeight - 5);
  apiListWrap.css("max-height", 4 * listHeight - 5);
});
