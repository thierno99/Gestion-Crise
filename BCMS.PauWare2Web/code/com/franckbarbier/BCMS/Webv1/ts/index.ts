window.onload = () => {
    // Tested with Tyrus 1.17 WebSockets Java library
    const service = new WebSocket("ws://localhost:1963/FranckBarbier/Server");

    const fm= document.querySelector(".fm"); 
    const pm= document.querySelector(".pm");
    const action=document.querySelector('.action');
    const title=document.querySelector('.content-title');
    const content=document.querySelector('.content');
    const send=document.querySelector('.form');
  


    fm?.addEventListener('click',()=>{
        service.send(JSON.stringify({logAsfm: "Fireman Man Logged"}));
        if(action!==null){
            if(action.classList.contains('hide')){
                action.classList.remove('hide');
            }

            if(title){
                title.innerHTML="Fireman Man Logged";
            }
            if(content)
            content.innerHTML=`
                <form class="form">
                    <div>
                        <h4>Precisez le nombre de véhicules à envoyer</h4>
                        <input type="text"/>
                    </div>
                    <div>
                        <h4>Cliquez pour choisir une route</h4>
                        <button type="button">Choisir une route</button>
                    </div>
                    <div>
                        <h4>Valider la route choisie</h4>
                        <button type="button">valider le choix de la route</button>
                    </div>
                    <div>
                        <h4>Dispatcher les véhicules de police</h4>
                        <button type="button">Dispatch</button>
                    </div>
                    <div>
                        <h4>break-down</h4>
                        <button type="button">Break-down</button>
                    </div>
                    <div>
                        <h4>Police Arrivé</h4>
                        <button type="button">arrive</button>
                    </div>
                    cl

                    <div>
                        <h4>recupere vehicule</h4>
                        <button type="button">recupere vehicule</button>
                    </div>

                    <button class="send" type="submit" >envoyer</button>
                </form>
                `;
        }
    })

    pm?.addEventListener('click',()=>{
        service.send(JSON.stringify({logAspm: "Police Man Logged"}));
        if(action!==null){
            if(action.classList.contains('hide')){
                action.classList.remove('hide');
            }
            if(title){
                title.innerHTML="Police Man Logged";
            }
            if(content)

                content.innerHTML=`
                <form class="form">
                    <div>
                        <h4>Precisez le nombre de véhicules à envoyer</h4>
                        <input type="text"/>
                    </div>
                    <div>
                        <h4>Cliquez pour choisir une route</h4>
                        <button type="button">Choisir une route</button>
                    </div>
                    <div>
                        <h4>Valider la route choisie</h4>
                        <button type="button">valider le choix de la route</button>
                    </div>
                    <div>
                        <h4>Dispatcher les véhicules de police</h4>
                        <button type="button">Dispatch</button>
                    </div>
                    <div>
                        <h4>break-down</h4>
                        <button type="button">Break-down</button>
                    </div>
                    <div>
                        <h4>Police Arrivé</h4>
                        <button type="button">arrive</button>
                    </div>
                    

                    <div>
                        <h4>recupere vehicule</h4>
                        <button type="button">recupere vehicule</button>
                    </div>

                    <input type="submit" value="Envoyer" class="send" />
                </form>
                `;
        }
    })

    
    console.log(send);
    send?.addEventListener('click',()=>{
        alert("fdgd");
    })



    service.onmessage = (event) => {
        console.log("Message from Java: " + event.data);
    };
    /*service.onopen = () => {
        console.log("service.onopen...");
        const response = window.confirm(service.url + " just opened... Say 'Hi!'?");
        if (response)
            service.send(JSON.stringify({Response: "Hi!"}));
    };*/
    
    //service.onclose = (event/*:CloseEvent*/) => {
      //  console.log("service.onclose... " + event.code);
       // window.alert("Bye! See you later...");
// '1011': the server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.
    //};
    service.onerror = () => {
        window.alert("service.onerror...");
    };
};