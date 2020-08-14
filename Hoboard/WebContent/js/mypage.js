// checkSpace
function checkSpace(str) {
  if (str.search(/\s/) != -1) return true;
  else return false;
}
// mypage id, name not can't change
$(document).ready(function () {
  // first get id, name from session
  const sessionName = $("#name").val();
  const sessionId = $("#id").val();
  const sessionEmail = $("#email").val();

  $("#updateBtn").on("click", function () {
    // member default info check
    var exit = false;
    $("input.textChk").each(function () {
      if ($(this).val().replace(/ /g, "") == "") {
        exit = true;
        alert($(this).siblings("label").text() + " 항목을 입력해주세요 !");
        $(this).focus();
        return false;
      } else if (checkSpace($(this).val())) {
        exit = true;
        alert("잘못된 입력입니다. 띄어쓰기를 사용할 수 없습니다.");
        return false;
      }
    });
    if (exit) return false;
    // on btn click get id, name from dom
    var sendName = $("#name").val();
    var sendId = $("#id").val();
    var sendEmail = $("#email").val();
    if (
      sessionName != sendName ||
      sessionId != sendId ||
      sessionEmail != sendEmail
    ) {
      alert("잘못된 시도입니다 !");
      return;
    } else if ($("#pw").val() != $("#pw_Check").val()) {
      alert("비밀번호를 확인해주세요 !");
      $("#pw").focus();
      return;
    } else {
      alert("개인정보 수정을 완료하였습니다 !");
      $("form").submit();
    }
  });
});
