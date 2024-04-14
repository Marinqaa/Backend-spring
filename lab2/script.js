const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];
function functions() {
    let news1 = document.getElementById("news1");
    let news2 = document.getElementById("news2");
    news1.innerHTML = welcome()+"<br/>"+date()+"<br/>";
    news2.innerHTML = "The author of this page will have a birthday in " + String(daysToBirthday()) + " days";
}
window.onload = function () {
    functions();
};
function welcome() {
    let today = new Date();
    let hour = today.getHours();
    if( (hour<18) && (hour>6) ) {
        return 'Good morning, ';
    } else {
        return 'Good evening, ';
    }
}

function date() {
    let today = new Date();
    let day =  today.getDate();
    let month = months[today.getMonth()];
    let year = today.getFullYear();

    return 'today is '+ month + ' ' +  day + ' ' + ' '  + year + '.';
}

function daysToBirthday() {
    let currentDate = new Date();
    let birthdayDay = 3;
    let birthdayMonth = 5;
    let currentYear = currentDate.getFullYear();
    let nextBirthday = new Date(currentYear, birthdayMonth - 1, birthdayDay);
    if (nextBirthday < currentDate) {
        nextBirthday = new Date(currentYear + 1, birthdayMonth - 1, birthdayDay);
    }
    let timeDiff = nextBirthday.getTime() - currentDate.getTime();
    let daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return daysDiff;
}

function clock() {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    if (minute < 10) minute = "0" + minute;
    if (second < 10) second = "0" + second;
    let footer = document.getElementById("footer");
    footer.innerHTML="&copy;2024 DG | " + hour + ":" + minute + ":" + second;

}