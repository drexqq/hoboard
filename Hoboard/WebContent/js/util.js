// checkSpace
function checkSpace(str) {
  if (str.search(/\s/) != -1) return true;
  else return false;
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
// check load value is 1 or 0
$("input[type='checkbox']").each(function () {
  if ($(this).val() == 1) {
    $(this).attr("checked", "checked");
  }
});

$("input.weekChk").each(function () {
  if ($(this).val() != "휴무") {
    $(this).addClass("show");
    $(this).siblings("label").addClass("on");
    $(this).siblings(".default-text").hide();
  } else if ($(this).attr("id") == "LUNCH") {
    if ($(this).val() != "없음") {
      $(this).addClass("show");
      $(this).siblings("label").addClass("on");
      $(this).siblings(".default-text").hide();
    }
  }
});

// daum post code
$("#findPostCode").on("click", function () {
  new daum.Postcode({
    oncomplete: function (data) {
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }
      document.getElementById("post_Num").value = data.zonecode;
      document.getElementById("address").value = addr;
      document.getElementById("d_Address").focus();
    },
  }).open();
});

// image validation check
function validateFileType(fileName) {
  var exName = fileName.split(".");
  var exType = exName[exName.length - 1];
  if (exType == "jpg" || exType == "jpeg" || exType == "png") return true;
  else return false;
}

// search
function search(page) {
  var choice = document.getElementById("choice").value;
  var word = document.getElementById("search").value;
  if (word == null || word == "") {
    document.getElementById("search").value = "";
    location.href = page;
  } else {
    location.href = page + "?searchWord=" + word + "&choice=" + choice;
  }
}

// Reserve_search
function R_search(page) {
 	let loc = document.getElementById("loc").value;
	let amt = document.getElementById("amt").value;
	let searchWord = document.getElementById("searchWord").value; 
  
  if (searchWord == null || searchWord == "") {
    document.getElementById("searchWord").value = "";
    location.href = page + "?loc=" + loc + "&amt=" + amt;
  }	else {
    location.href = page + "?searchWord=" + searchWord + "&loc=" + loc + "&amt=" + amt;
  }
}

// reserve paging
function goRPage(pageName, pageNum) {
  var loc = document.getElementById("loc").value;
  var amt = document.getElementById("amt").value;
  var searchWord = document.getElementById("searchWord").value;
  
  if (loc != null && loc != "" && amt != null && amt != ""  && searchWord != null && searchWord != "") {
    location.href =
      pageName +
      "?searchWord=" +
      searchWord +
      "&loc=" +
      loc +
      "&amt=" +
      amt +
      "&page=" +
      pageNum;
  } else if (searchWord == null || searchWord == "") {
    location.href = pageName + "?page=" + pageNum;
  }
}


// paging
function goPage(pageName, pageNum) {
  var choice = document.getElementById("choice").value;
  var word = document.getElementById("search").value;
  if (choice != null && choice != "" && word != null && word != "") {
    location.href =
      pageName +
      "?searchWord=" +
      word +
      "&choice=" +
      choice +
      "&page=" +
      pageNum;
  } else if (word == null || word == "") {
    location.href = pageName + "?page=" + pageNum;
  }
}

// input enter key press -> search()
function enter(pageName) {
  if (event.keyCode == "13") {
    search(pageName);
  }
}
