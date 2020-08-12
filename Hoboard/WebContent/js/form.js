// checkSpace
function checkSpace(str) {
  if (str.search(/\s/) != -1) {
    return true;
  } else {
    return false;
  }
}
// toggle check box color
$(".time-wrap")
  .find("label")
  .each(function () {
    $(this).on("click", function () {
      $(this).toggleClass("on");
      $(this).siblings("input").toggleClass("show").val("");
      $(this).siblings(".default-text").toggle();
    });
  });
// id_check
$("#id_Check").on("click", function () {
  console.log($("#id").val());
  if ($("#id").val() == "") {
    alert("아이디를 입력해주세요 !");
    return false;
  } else if (checkSpace($("#id").val())) {
    alert("잘못된 입력입니다. 띄어쓰기를 사용할 수 없습니다.");
    $("#id").val("");
    return false;
  } else {
    console.log("ajax start");
    $.ajax({
      type: "GET",
      url: "MEMBER",
      datatype: "json",
      data: { id: $("#id").val() },
      success: function (json) {
        if (json.chk) {
          alert("중복된 아이디입니다. 다른 아이디를 입력해주세요 !");
          $("#id").val("").focus();
        } else {
          alert("확인되었습니다 !");
          $("#id").attr("readonly", "readonly");
          $("#id_Check").addClass("done").attr("disabled", "disabled");
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
});

// before form submit input value check
$("#joinBtn").on("click", function () {
  // auth 1 - INDVD / 2 - BUSI
  const auth = $("input[name=auth]").val();

  // member default info chk

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
  if (!$("#id_Check").hasClass("done")) {
    alert("아이디 체크를 해주세요 !");
    return;
  } else if ($("#pw").val() != $("#pw_Check").val()) {
    alert("비밀번호를 확인해주세요 !");
    $("#pw").focus();
    return;
  } else {
    $("form").submit();
  }
});
