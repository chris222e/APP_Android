<?php
header('Content-Type: application/json');
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "constructora";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    echo json_encode(array("success" => false));
    exit();
}

$json = file_get_contents('php://input');
$data = json_decode($json);

if ($data) {
    $id = $data->id;
    $nombre = $data->nombre;
    $marca = $data->marca;
    $descripcion = $data->descripcion;
    $condicion = $data->condicion;
    $tipo = $data->tipo;

    $sql = "UPDATE herramientas SET 
            nombre='$nombre', 
            marca='$marca', 
            descripcion='$descripcion', 
            condicion='$condicion', 
            tipo='$tipo' 
            WHERE idherramienta=$id";

    if ($conn->query($sql) === TRUE) {
        echo json_encode(array("success" => true));
    } else {
        echo json_encode(array("success" => false));
    }
}
$conn->close();
?>