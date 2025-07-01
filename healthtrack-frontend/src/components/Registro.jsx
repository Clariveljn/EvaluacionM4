import { useState } from "react";
import axios from "axios";

function Registro() {
    const [nombre, setNombre] = useState("");
    const [peso, setPeso] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/usuarios/registrar", {
                nombre,
                peso: parseFloat(peso),
            });
            alert("Usuario registrado con éxito.");
        } catch (error) {
            alert("Error al registrar: " + error.response.data.message);
        }
    };

    return (
        <div>
            <h2>Registro de Usuario</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                <input type="number" placeholder="Peso" value={peso} onChange={(e) => setPeso(e.target.value)} required />
                <button type="submit">Registrar</button>
            </form>
        </div>
    );
}

export default Registro;
