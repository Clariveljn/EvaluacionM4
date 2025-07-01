import { useState } from "react";
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL
});

function ActualizarPeso() {
    const [nombre, setNombre] = useState("");
    const [nuevoPeso, setNuevoPeso] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await api.put(`/api/usuarios/actualizar/${nombre}?nuevoPeso=${nuevoPeso}`);
            alert("Peso actualizado con éxito.");
        } catch (error) {
            alert("Error al actualizar: " + (error.response?.data?.message || error.message));
        }
    };

    return (
        <div>
            <h2>Actualizar Peso</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                    required
                />
                <input
                    type="number"
                    placeholder="Nuevo Peso"
                    value={nuevoPeso}
                    onChange={(e) => setNuevoPeso(e.target.value)}
                    required
                />
                <button type="submit">Actualizar</button>
            </form>
        </div>
    );
}

export default ActualizarPeso;

