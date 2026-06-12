<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "constructora";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die(json_encode(array("success" => false)));
}

// Recibir el JSON de la aplicación
$json = file_get_contents('php://input');
$data = json_decode($json);

if ($data) {
    $nombre = $data->nombre;
    $marca = $data->marca;
    $descripcion = $data->descripcion;
    $condicion = $data->condicion;
    $tipo = $data->tipo;

    // Insertar en tu tabla 'herramientas'
    $sql = "INSERT INTO herramientas (nombre, marca, descripcion, condicion, tipo) 
            VALUES ('$nombre', '$marca', '$descripcion', '$condicion', '$tipo')";

    if ($conn->query($sql) === TRUE) {
        echo json_encode(array("success" => true));
    } else {
        echo json_encode(array("success" => false, "error" => $conn->error));
    }
} else {
    echo json_encode(array("success" => false, "message" => "Sin datos"));
}

$conn->close();
?>