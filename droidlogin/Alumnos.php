<?php

/**
 * Representa el la estructura de las Alumnoss
 * almacenadas en la base de datos
 */
require 'Database.php';

class Alumnos
{
    function __construct()
    {
    }

    /**
     * Retorna en la fila especificada de la tabla 'Alumnos'
     *
     * @param $id Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT * FROM usuarios";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $id Identificador del alumno
     * @return mixed
     */
    public static function getById($id)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT id,
                            username,
                            password
                             FROM usuarios
                             WHERE usuarios = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($id));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $id     identificador
     * @param $username     nuevo nombre
     * @param $password nueva direccion
     
     */
    public static function update(
        $id,
        $username,
        $password
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE usuarios" .
            " SET username=?, password=? " .
            "WHERE id=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($username, $password, $id));

        return $cmd;
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $username      nombre del nuevo registro
     * @param $password dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insert(
        $username,
        $password
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO usuarios ( " .
            "username," .
            " password)" .
            " VALUES( ?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $username,
                $password
            )
        );

    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $id identificador de la tabla Alumnos
     * @return bool Respuesta de la eliminación
     */
    public static function delete($id)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM usuarios WHERE id=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($id));
    }
}

?>