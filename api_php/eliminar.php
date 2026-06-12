<?php
// Permitir que la App reciba la respuesta como JSON
header('Content-Type: application/json');

// Datos de conexión a tu base de datos 'constructora'
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "constructora";

// Crear la conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar si hay error de conexión
if ($conn->connect_error) {
    echo json_encode(array("success" => false, "message" => "Fallo de conexión"));
    exit();
}

// Obtener el ID que envía la App desde la URL (ej: eliminar.php?id=5)
if (isset($_GET['id'])) {
    $id = $_GET['id'];

    // Consulta para eliminar. Usamos 'idherramienta' que es el nombre de tu columna
    $sql = "DELETE FROM herramientas WHERE idherramienta = $id";

    if ($conn->query($sql) === TRUE) {
        // Si se eliminó correctamente en MySQL
        echo json_encode(array("success" => true));
    } else {
        // Si hubo un error en la consulta SQL
        echo json_encode(array("success" => false, "error" => $conn->error));
    }
} else {
    // Si no se recibió el ID en la URL
    echo json_encode(array("success" => false, "message" => "No se recibió el ID"));
}

// Cerrar conexión
$conn->close();
?>