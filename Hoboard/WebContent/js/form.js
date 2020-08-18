
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
$("#joinBtn").on("click", function () {
  // auth 1 - INDVD / 2 - BUSI
  const auth = $("input[name=auth]").val();

  // member default info check
  var exit = false;
  $("input.textChk").not(".address").each(function () {
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


