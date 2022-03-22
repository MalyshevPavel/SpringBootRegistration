function equals() {
    var form = document.getElementById('form1');
    if (form.newPassword.value != form.passConfirm.value) {
        alert("Пароли не совпадают!");
        return false;
    }
    return true;
}

function equalsCheck() {
    var form = document.getElementById('form2');
    if (form.password.value != form.matchingPassword.value) {
        alert("Пароли не совпадают!");
        return false;
    }
    return true;
}