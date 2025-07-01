import { useState } from "react";
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL
});

function Registro() {
  const [nombre, setNombre] = useState("");
  const [peso, setPeso] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/api/usuarios/registrar", {
        nombre,
        peso: parseFloat(peso),
      });
      alert("Usuario registrado con éxito.");
    } catch (error) {
      alert("Error al registrar: " + (error.response?.data?.message || error.message));
    }
  };

  return (
    <div>
      <h2>Registro de Usuario</h2>
      <form onSubmit={handleSubmit}>
        <input
          id="nombre-registro"
          type="text"
          placeholder="Nombre"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          required
        />
        <input
          id="peso-registro"
          type="number"
          placeholder="Peso"
          value={peso}
          onChange={(e) => setPeso(e.target.value)}
          required
        />
        <button type="submit" id="boton-registrar">Registrar</button>
      </form>
    </div>
  );
}

export default Registro;


