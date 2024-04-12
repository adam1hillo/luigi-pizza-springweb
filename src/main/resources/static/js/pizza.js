"use strict"
import {byId, toon, setText, verberg} from "./util.js";

byId("zoek").onclick = async function () {
    verbergPizzaEnFouten();
    const zoekIdInput = byId("zoekId");
    if (zoekIdInput.checkValidity()) {
        findById(zoekIdInput.value)
    } else {
        toon("zoekIdFout");
        zoekIdInput.focus();
    }
}

function verbergPizzaEnFouten() {
    verberg("pizza");
    verberg("storing")
    verberg("nietGevonden");
    verberg("zoekIdFout");
}
async function findById(id) {
    const response = await fetch(`pizzas/${id}`);
    if (response.ok) {
        const pizza = await response.json();
        toon("pizza");
        setText("naam", pizza.naam);
        setText("prijs", pizza.prijs);
    } else {
        if (response.status === 404) {
            toon("nietGevonden");
        } else {
            toon("storing");
        }
    }
}