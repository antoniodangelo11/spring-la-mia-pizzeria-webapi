/* COSTANTI*/
const apiUrl = "http://localhost:8080/api/v1/pizzas";
const root = document.getElementById("root");

/* FUNZIONI */
const renderIngredients = (ingredients) => {
  console.log(ingredients);
  let content;
  if (ingredients.length === 0) {
    content = "No ingredients";
  } else {
    content = '<ul class="list-unstyled">';
    ingredients.forEach((ing) => {
      content += `<li>${ing.name}</li>`;
    });
    content += "</ul>";
  }
  return content;
};


const renderPizza = (element) => {
  console.log(element);
  return `<div class="card shadow h-100">
    <div class="card-body">
        <h5 class="card-title">${element.name}</h5>
        <p class="card-text">${
          element.description != "" ? element.description : "N.D."
        }</p>
        <div>
            <img class="h-100 w-100" src="${element.photoUrl}" alt="">
        </div>
        <p>${element.price}€</p>
    </div>
    <div class="card-footer">${renderIngredients(element.ingredients)}</div>
    <br>
    <button class="btn btn-danger" onclick="deletePizza(${
      element.id
    })">Delete Pizza</button>
  </div>`;
};


const renderPizzaList = (data) => {
  let content;
  console.log(data);
  if (data.length > 0) {
    content = '<div class="row">';
    data.forEach((element) => {
      content += '<div class="col-3">';
      content += renderPizza(element);
      content += "</div>";
    });
    content += "</div>";
  } else {
    content = '<div class="alert alert-info">The list is empty</div>';
  }
  root.innerHTML = content;
};


const getPizzas = async () => {
  try {
    const response = await axios.get(apiUrl);
    renderPizzaList(response.data);
  } catch (error) {
    console.log(error);
  }
};

// Funzione per eliminare la pizza
const deletePizza = async (pizzaId) => {
  try {
    const response = await fetch(`${apiUrl}/${pizzaId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            // Aggiungi eventuali altri header necessari
        },
    });

    if (!response.ok) {
        throw new Error(`Errore durante la cancellazione della pizza con ID ${pizzaId}`);
    }

    console.log(`Pizza con ID ${pizzaId} eliminata con successo`);
    // Puoi aggiornare la visualizzazione o eseguire altre azioni dopo la cancellazione
    location.reload();
  } catch (error) {
    console.error(error.message);
    // Gestisci l'errore in base alle tue esigenze
}
};

getPizzas();

