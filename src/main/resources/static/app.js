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

function webSocketConnect(authToken) {
    console.log("Auth Token: " + authToken);
    var socket = new SockJS('/websocket/connect?authToken=' + authToken);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        setConnected(true);
        stompClient.subscribe('/topic/greetings', function () {
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
    stompClient.send("/api/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        webSocketConnect("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAxMTIyNSIsInJvbGUiOiJBRE1JTklTVFJBVE9SIiwiaWF0IjoxNjQ4NDYwNDgyLCJleHAiOjE2NDg1MjA0ODJ9.aCHusaRO-uwnqd-o9uOdeCfjfoNgqdpg45NbfeAVVe6VZ0kNhNwWqoGjixGRrhxB42XeAd0UJKFj3EkED-fb9g");
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});
