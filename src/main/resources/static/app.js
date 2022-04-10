var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

// function connect() {
//     $.ajax({
//         url: '/authentication/token',
//         headers: {"Authorization": "SuperSecureToken"},
//         success: function (result) {
//             webSocketConnect(result)
//         }
//     })
// }

function webSocketConnect(authToken) {
    console.log("Auth Token: " + authToken);
    var socket = new SockJS('/websocket/connect?authentication=' + authToken);
    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
    stompClient.connect({}, function () {
        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
        stompClient.subscribe('/topic/greetings', function () {
//            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
//        connect();
        webSocketConnect("1dfa537f-d46f-451e-9daa-ce26a58c6583");
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});
