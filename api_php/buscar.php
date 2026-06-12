<?php
header('Content-Type: application/json');
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "constructora";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    echo json_encode(array("error" => "Conexión fallida"));
    exit();
}

if (isset($_GET['id'])) {
    $id = $_GET['id'];
    // Usamos idherramienta que es como se llama en tu tabla
    $sql = "SELECT * FROM herramientas WHERE idherramienta = $id";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo json_encode($row);
    } else {
        echo json_encode(array("error" => "No se encontró la herramienta"));
    }
}
$conn->close();
?>
