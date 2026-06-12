<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "constructora";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die(json_encode(array("success" => false, "message" => "Fallo de conexión")));
}

$sql = "SELECT * FROM herramientas"; // Asegúrate que tu tabla se llame así
$result = $conn->query($sql);

$data = array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

echo json_encode(array("success" => true, "data" => $data));
$conn->close();
?>
