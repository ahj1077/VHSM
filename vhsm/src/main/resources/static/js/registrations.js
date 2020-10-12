function diff_pw() {
    alert("동일한 비밀번호를 입력해주세요.");
}

function nullcell() {
    alert("%s 입력되지 않았습니다.");
}

function ifsamepw(pw1, pw2) {
    var comment = document.querySelector('pwcomment');
    if (pw2 != pw1) {
            comment = "동일한 비밀번호를 입력해주세요.";
            document.getElementById('pwcomment').innerHTML = comment;
    }
    else if (pw2 === pw1) {
        comment = "";
        document.getElementById('pwcomment').innerHTML = comment;
    }
}

function checkbeforesubmit() {
    ifsamepw(pw1, pw2);
}