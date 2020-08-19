// check email, id
$(".check_dup").on("click", function () {
  var check_eng = $(this).data("name");
  var check_kor = check_eng == "id" ? "아이디" : "이메일";
  if ($("#" + check_eng).val() == "") {
    alert(check_kor + "을 입력해주세요 !");
    return false;
  } else if (checkSpace($("#" + check_eng).val())) {
    alert("잘못된 입력입니다. 띄어쓰기를 사용할 수 없습니다.");
    $(this).val("");
    return false;
  } else {
    $.ajax({
      type: "GET",
      url: "MEMBER?chk=" + check_eng,
      datatype: "json",
      data: { check_kor: $("#" + check_eng).val() },
      success: function (json) {
        if (json.chk) {
          alert(
            "중복된 " +
              check_kor +
              "입니다. 다른 " +
              check_kor +
              "를 입력해주세요 !"
          );
          $("#" + check_eng)
            .val("")
            .focus();
        } else {
          alert("사용할 수 있는 " + check_kor + "입니다 !");
          $("#" + check_eng).attr("readonly", "readonly");
          $(".check_dup[data-name=" + check_eng + "]")
            .addClass("done")
            .attr("disabled", "disabled");
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
});
// before form submit input value check
const auth = $("input[name=auth]").val();
$("#joinBtn").on("click", function () {
  // auth 1 - INDVD / 2 - BUSI

  // member default info check
  var exit = false;
  $("input.textChk")
    .not(".address")
    .each(function () {
      if ($(this).val().replace(/ /g, "") == "") {
        exit = true;
        alert($(this).siblings("label").text() + " 항목을 입력해주세요 !");
        $(this).focus();
        return false;
      } else if (checkSpace($(this).val())) {
        exit = true;
        alert("잘못된 입력입니다. 띄어쓰기를 사용할 수 없습니다.");
        $(this).focus();
        return false;
      }
    });
  if (exit) return false;
  // auth check
  if (auth == 1) {
  } else if (auth == 2) {
    // 진료시간 check
    if (
      $("input.weekChk").not("input#lunch").siblings("label.on").length == 0
    ) {
      alert("최소 하루 이상의 진료시간을 정해주세요 !");
      return;
    }
    exit = false;
    $("input.weekChk").each(function () {
      if (
        $(this).siblings("label").hasClass("on") &&
        $(this).val().replace(/ /g, "") == ""
      ) {
        var msg = " 진료시간을 입력해주세요 !";
        if ($(this).attr("id") == "lunch") msg = "을 입력해주세요 !";

        $(this).focus();
        alert($(this).siblings("label").find("span").text() + msg);

        exit = true;
        return false;
      }
    });
    if (exit) return false;

    // 진료 분야 최소 1개 이상 check
    if ($("input.cateChk:checked").not("#lunch").length == 0) {
      alert("최소 1개 이상의 진료 분야를 선택해주세요 !");
      return;
    }
  } else {
    alert("잘못된 접근입니다 !");
    location.href = "index.jsp";
  }
  if ($("#pw").val() != $("#pw_Check").val()) {
    alert("비밀번호를 확인해주세요 !");
    $("#pw").focus();
    return;
  } else {
    $("form").submit();
  }
});

// input placeholder
$("input#name").attr("placeholder", "이름을 입력해주세요.");
$("input#id").attr("placeholder", "사용하실 ID를 입력해주세요.");
$("input#pw").attr("placeholder", "비밀번호를 입력해주세요.");
$("input#pw_Check").attr("placeholder", "비밀번호를 확인해주세요.");
$("input#tel").attr("placeholder", "전화번호(휴대폰번호)를 입력해주세요.");
$("input#email").attr("placeholder", "'-'를 제외한 숫자만 입력해주세요.");
$("input#post_Num").attr("placeholder", "우편번호");
$("input#address").attr("placeholder", "주소");
$("input#d_Address").attr("placeholder", "상세주소");

// post_Num or address click -> find
$("input#post_Num, input#address").on("click", function () {
  $("#findPostCode").click();
});

// busi member logo, hompage
var fileValue, fileName;
$("input#logo, #fileBtn").on("click", function () {
  $("input#fileAdd").click();
});
$("input#fileAdd").on("change", function () {
  fileValue = $("input#fileAdd").val().split("\\");
  fileName = fileValue[fileValue.length - 1];
  console.log(fileName);
  console.log(fileValue);
  if (validateFileType(fileName)) $("input#logo").val(fileName);
  else alert("JPG, JPEG, PNG 형식으로만 로고를 올릴 수 있습니다 !");
});

if (auth == 2) {
  $("input#logo").attr("placeholder", "파일명").css("cursor", "pointer");
  $("input#homepage").attr("placeholder", "홈페이지 URL");
}
