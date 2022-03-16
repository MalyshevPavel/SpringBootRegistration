function equals() {
    var form1 = document.getElementById('form1');
    if (form1.newPassword.value != form1.passConfirm.value) {
        alert("Password mismatch!");
        return false;
    }
    return true;
}