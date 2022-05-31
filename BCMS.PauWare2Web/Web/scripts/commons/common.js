/**
 * Permet de creer n'importe que types d'element html
 * @param typeElt
 * @returns
 */
export const CreatElt = (typeElt) => {
    const newdiv = document.createElement(typeElt);
    return newdiv;
};
/**
 * permet d'ajouter un label un input
 * @param elt
 * @param label
 */
export const addLabel = (elt, label) => {
    elt.appendChild(document.createTextNode(label));
};
/**
 * permet simplement de creer des input html avec different type: {button, text, number,...}
 * @param type
 * @returns
 */
export const creatInput = (type) => {
    const input = CreatElt('input');
    input.setAttribute('type', type);
    return input;
};
/**
 * permet de creer un bouton avec les classes et toutes les information passées comme params
 * @param label
 * @param textcontent
 * @param classe
 * @returns
 */
export const createBtn = (label, textcontent, classe) => {
    const div = CreatElt('div');
    const LabelRoad = CreatElt('h4');
    addLabel(LabelRoad, (label));
    const inputroad = CreatElt('button');
    inputroad.setAttribute("type", "button");
    inputroad.innerHTML = textcontent;
    inputroad.classList.add('button');
    inputroad.classList.add(classe); //sr: select road
    div.appendChild(LabelRoad);
    div.appendChild(inputroad);
    return div;
};
/**
 * permet de créer un button de classe primaire et de type submit
 * @returns
 */
export const btnprimary = () => {
    const div = CreatElt('div');
    const submit = CreatElt('button');
    submit.innerHTML = "Valider le choix";
    submit.setAttribute("type", "submit");
    submit.classList.add('primary');
    submit.classList.add('sendform');
    div.appendChild(submit);
    return div;
};
/**
 * permet de gérer le nombre de véhicules à envoyer pour gérer la crise.
 * @param rescuer
 * @param content
 * @param dest
 * @param contentValue
 */
export const sendNbCar = (rescuer, content, dest, contentValue) => {
    const div = CreatElt('div');
    const btn = btnprimary();
    const LabelNbvehic = CreatElt('h4');
    addLabel(LabelNbvehic, ("Precisez le nombre de véhicules de " + contentValue + " à envoyer"));
    const inputNbCar = creatInput('number');
    inputNbCar.setAttribute('placeholder', 'nombre de vehicules');
    div.appendChild(LabelNbvehic);
    div.appendChild(inputNbCar);
    const value = CreatElt('div');
    value.classList.add('flex');
    value.appendChild(div);
    value.appendChild(btn);
    btn.addEventListener('click', (e) => {
        rescuer.set_nbCarsToSend(div.children[1].valueAsNumber);
    });
    dest.addEventListener('click', () => {
        content.innerHTML = "";
        content.appendChild(value);
    });
};
/**
 * choisir la route que la police doit suivre
 * @param rescuer
 * @param content
 * @param dest
 */
export const SelectRoadForPolice = (rescuer, content, dest) => {
    const btn = createBtn("Cliquez pour Choisir Une route pour les véhicules de Police", "Choisir Une route", "sr");
    dest.addEventListener('click', (e) => {
        content.innerHTML = "";
        content.appendChild(btn);
    });
    if (!rescuer.get_isRoadChosed())
        btn.addEventListener('click', () => {
            rescuer.set_isRoadValide(true);
            rescuer.set_isRoadChosed(true);
        });
};
/**
 * la ploice propose une route auw pompier et attendent leurs retours concernant la route choisie
 * @param rescuer
 * @param fireman
 * @param content
 * @param dest
 * @param contentv
 */
export const SelectRoadForFireMans = (rescuer, fireman, content, dest, contentv) => {
    const btn = contentv === "Police" ? createBtn("Cliquez pour Choisir Une route pour les véhicules des Pompiers", "Choisir Une route", "srp")
        : createBtn("Cliquez pour Valider La route Choisie", "Valider Le choix de la route", "srp");
    createBtn("Cliquez pour Choisir Une route pour les véhicules des Pompiers", "Choisir Une route", "srp");
    dest.addEventListener('click', (e) => {
        content.innerHTML = "";
        content.appendChild(btn);
    });
    if (contentv === "Police" && !fireman.get_isRoadChosed())
        btn.addEventListener('click', () => {
            fireman.set_isRoadValide(true);
            fireman.set_isRoadChosed(true);
            console.log(rescuer, fireman);
        });
    if (contentv === "Pompier" && fireman.get_islog()) {
        btn.addEventListener('click', () => {
            fireman.set_isRoadValide(true);
            fireman.set_isRoadChosed(true);
            rescuer.set_okayWithRoad(true);
            fireman.set_okayWithRoad(true);
            console.log(rescuer, fireman);
        });
    }
};
/**
 * dispatcher les véhicules
 * @param rescuer
 * @param content
 * @param dest
 */
export const DispatchCars = (rescuer, content, dest) => {
    const btn = createBtn("Cliquez pour Dispatcher les véhicules des " + rescuer.get_Name(), "Dispacther", "dsp");
    dest.addEventListener('click', (e) => {
        content.innerHTML = "";
        content.appendChild(btn);
    });
    btn.addEventListener('click', () => {
        rescuer.set_Dispatch(true);
        console.log(rescuer);
    });
};
/**
 * les pompier ne sont pas d'accord avec la route roposer par la Police
 * @param rescuer
 * @param fireman
 * @param content
 * @param dest
 * @param contentv
 */
export const DesagressWithPoliceRender = (rescuer, fireman, content, dest, contentv) => {
    const btn = createBtn("Cliquez pour Choisir Une nouvelle  Routes", "Choisir Une route", "srp");
    dest.addEventListener('click', () => {
        content.innerHTML = "";
        content.appendChild(btn);
    });
    btn.addEventListener('click', () => {
        fireman.set_isRoadChosed(true);
        fireman.set_isRoadValide(true);
        rescuer.set_okayWithRoad(false);
        fireman.set_okayWithRoad(true);
        console.log(rescuer, fireman);
    });
};
/**
 * cree une balise i fontAwesome
 * @param str
 * @returns
 */
export const CreatItag = (str) => {
    const i = CreatElt('i');
    i.classList.add('fas');
    i.classList.add(str);
    return i;
};
export const RenderActions = (police, fireman, action, content, contentValue, btnname) => {
    const btn = CreatElt('button');
    let msg = (btnname === "pm") ? police.get_Name() : fireman.get_Name();
    btn.innerHTML = "Se Connecter en tant que " + msg;
    btn.classList.add('btn');
    btn.classList.add(btnname);
    const li1 = CreatElt('li');
    li1.innerHTML = "Envoyer les vehicules";
    const li_ = CreatElt('li');
    li_.innerHTML = "choisir une autres routes";
    const li2 = CreatElt('li');
    //if(btnname==="pm" || (btnname!=="pm" && !fireman.get_okayWithRoad()))
    li2.innerHTML = "Choisir Une route pour les véhicules de" + contentValue.value;
    const li3 = CreatElt('li');
    let txt = (btnname === "pm") ? "choisir Une route pour les camions de pompier" : "Prendre la routes choisie par la police";
    li3.innerHTML = txt;
    const li4 = CreatElt('li');
    li4.innerHTML = "Dispatcher les véhicules de " + contentValue.value;
    const li5 = CreatElt('li');
    li5.innerHTML = "Confirmer l'arrivée des Véhicules de " + contentValue.value;
    const li6 = CreatElt('li');
    li6.innerHTML = "Signaler la panne d'un Véhicule";
    let str;
    if ((contentValue.value === "Police" && police.get_nbCarsToSend() > 0) || (contentValue.value === "Pompier" && fireman.get_nbCarsToSend() > 0)) {
        str = "fa-check-circle";
        li1.classList.add('noevent');
    }
    else
        str = "fa-exclamation-triangle";
    li1.appendChild(CreatItag(str));
    if ((contentValue.value === "Police" && police.get_isRoadChosed()) || (contentValue.value === "Pompier" && fireman.get_isRoadChosed())) {
        str = "fa-check-circle";
        li2.classList.add('noevent');
    }
    else
        str = "fa-exclamation-triangle";
    li2.appendChild(CreatItag(str));
    if ((contentValue.value === "Police" && fireman.get_isRoadChosed()) || (contentValue.value === "Pompier" && fireman.get_isRoadChosed() && fireman.get_okayWithRoad())) {
        li3.classList.add('noevent');
        str = "fa-check-circle";
    }
    else
        str = "fa-exclamation-triangle";
    li3.appendChild(CreatItag(str));
    if ((contentValue.value === "Police" && police.get_Dispatch()) || (contentValue.value === "Pompier" && fireman.get_Dispatch())) {
        li4.classList.add('noevent');
        str = "fa-check-circle";
    }
    else
        str = "fa-exclamation-triangle";
    li4.appendChild(CreatItag(str));
    li5.appendChild(CreatItag('fa-exclamation-triangle'));
    li6.appendChild(CreatItag('fa-exclamation-triangle'));
    if ((contentValue.value === "Pompier" && fireman.get_okayWithRoad())) {
        li_.classList.add('noevent');
        str = "fa-check-circle";
    }
    else
        str = "fa-exclamation-triangle";
    li_.appendChild(CreatItag(str));
    if ((!police.get_islog() && btnname === "pm") || (!fireman.get_islog() && btnname === "fm")) {
        li1.classList.add('notAllowed');
        li2.classList.add('notAllowed');
        li3.classList.add('notAllowed');
        li4.classList.add('notAllowed');
        li5.classList.add('notAllowed');
        li6.classList.add('notAllowed');
        li_.classList.add('notAllowed');
        // if(btn.classList.contains('notAllowed'))
        // btn.classList.remove('notAllowed');
    }
    else {
        btn.classList.add('notAllowed');
        btn.setAttribute("disabled", "desabled");
    }
    btn.addEventListener('click', () => {
        if (li1.classList.contains('notAllowed'))
            li1.classList.remove('notAllowed');
        if (li2.classList.contains('notAllowed'))
            li2.classList.remove('notAllowed');
        if (li3.classList.contains('notAllowed'))
            li3.classList.remove('notAllowed');
        if (li4.classList.contains('notAllowed'))
            li4.classList.remove('notAllowed');
        if (li5.classList.contains('notAllowed'))
            li5.classList.remove('notAllowed');
        if (li6.classList.contains('notAllowed'))
            li6.classList.remove('notAllowed');
        if (li_.classList.contains('notAllowed'))
            li_.classList.remove('notAllowed');
        btn.classList.add('notAllowed');
        btn.setAttribute("disabled", "desabled");
    });
    action.appendChild(btn);
    action.appendChild(li1);
    if (btnname === "pm")
        action.appendChild(li2);
    if (btnname === "fm")
        action.appendChild(li_);
    action.appendChild(li3);
    if (contentValue.value === "Police" && !police.get_isRoadValide())
        action.appendChild(li3);
    action.appendChild(li4);
    action.appendChild(li5);
    action.appendChild(li6);
    if (contentValue.value === "Police")
        sendNbCar(police, content, li1, police.get_Name()); //li1: nb cars
    else
        sendNbCar(fireman, content, li1, fireman.get_Name()); //li1: nb cars
    if (btnname === "pm")
        SelectRoadForPolice(police, content, li2);
    SelectRoadForFireMans(police, fireman, content, li3, contentValue.value);
    if (contentValue.value === "Police")
        DispatchCars(police, content, li4); //li1: nb cars
    else
        DispatchCars(fireman, content, li4); //li1: nb cars  
    if (contentValue !== "Police")
        DesagressWithPoliceRender(police, fireman, content, li_, contentValue.value);
    // li5.addEventListener('click',(e:Event)=>{
    // content.innerHTML="";
    //   content.appendChild(createBtn("Cliquez pour Choisir Une route pour les véhicules des Pompiers","Choisir Une route","srp"));
    // })
    // li6.addEventListener('click',(e:Event)=>{
    // content.innerHTML="";
    //   content.appendChild(createBtn("Cliquez pour Choisir Une route pour les véhicules des Pompiers","Choisir Une route","srp"));
    // })    
};
export const HandleContent = (police, fireman, title, content, contentValue) => {
    let msg = contentValue.value === "Police" ? police.get_Name() : fireman.get_Name();
    console.log(content);
    content.innerHTML = "";
    title.innerHTML = contentValue.title;
    const formcontent = CreatElt('form');
    formcontent.classList.add('form');
    const div1 = CreatElt('div');
    const LabelNbvehic = CreatElt('h4');
    addLabel(LabelNbvehic, ("Precisez le nombre de véhicules de " + contentValue.value + " à envoyer"));
    const inputNbCar = creatInput('number');
    inputNbCar.setAttribute('placeholder', 'nombre de vehicules');
    div1.appendChild(LabelNbvehic);
    div1.appendChild(inputNbCar);
    /**------------------------------ */
    const div2 = CreatElt('div');
    const LabelRoad = CreatElt('h4');
    addLabel(LabelRoad, ("Cliquez pour choisir une route"));
    const inputroad = CreatElt('button');
    inputroad.setAttribute("type", "button");
    inputroad.innerHTML = "Choisir Une route";
    inputroad.classList.add('button');
    inputroad.classList.add('sr'); //sr: select road
    div2.appendChild(LabelRoad);
    div2.appendChild(inputroad);
    /**------------------------------ */
    const div3 = CreatElt('div');
    const LabelValidRoad = CreatElt('h4');
    addLabel(LabelValidRoad, ("Cliquez pour Valider la route choisie"));
    const inputvalidroad = CreatElt('button');
    inputvalidroad.setAttribute("type", "button");
    inputvalidroad.innerHTML = "Prendre la route choisie par la police";
    inputvalidroad.classList.add('button');
    inputvalidroad.classList.add('vr'); //sr: vilid road
    div3.appendChild(LabelValidRoad);
    div3.appendChild(inputvalidroad);
    /**----------****************-------------- */
    const div_ = CreatElt('div');
    const LabeldesagressRoad = CreatElt('h4');
    addLabel(LabeldesagressRoad, ("Cliquez pour s'opposer à la route choisie"));
    const inputdesagressroad = CreatElt('button');
    inputdesagressroad.setAttribute("type", "button");
    inputdesagressroad.innerHTML = "choisir une autre route";
    inputdesagressroad.classList.add('button');
    inputdesagressroad.classList.add('vr'); //sr: vilid road
    div_.appendChild(LabeldesagressRoad);
    div_.appendChild(inputdesagressroad);
    /**------------------------------ */
    const div4 = CreatElt('div');
    const Labeldispatchc = CreatElt('h4');
    addLabel(Labeldispatchc, ("Dispatcher les véhicules de " + contentValue.value));
    const dispatch = CreatElt('button');
    dispatch.setAttribute("type", "button");
    dispatch.innerHTML = "Dispatcher";
    dispatch.classList.add('button');
    inputroad.classList.add('Dispatch');
    div4.appendChild(Labeldispatchc);
    div4.appendChild(dispatch);
    /**------------------------------ */
    const div5 = CreatElt('div');
    const submit = CreatElt('button');
    submit.innerHTML = "Valider le choix";
    submit.setAttribute("type", "submit");
    submit.classList.add('primary');
    submit.classList.add('sendform');
    div5.appendChild(submit);
    /**------------------------------ */
    formcontent.appendChild(div1);
    /*formcontent.appendChild(div2);
    formcontent.appendChild(div3);
    formcontent.appendChild(div4);*/
    formcontent.appendChild(div5);
    div5.addEventListener('click', (e) => {
        e.preventDefault();
        const nbvehicul = div1.children[1].valueAsNumber;
        if (nbvehicul <= 4 && nbvehicul > 0) {
            formcontent.removeChild(div1);
            formcontent.removeChild(div5);
            if (contentValue.value === "Police")
                police.set_nbCarsToSend(nbvehicul); //definir le nombre de vehicules à envoyer
            else
                fireman.set_nbCarsToSend(nbvehicul);
            alert("envoyer avec succès!");
            if (contentValue.value === "Police" && (!fireman.get_islog() && !fireman.get_okayWithRoad()))
                formcontent.appendChild(div2);
            else {
                formcontent.appendChild(div3);
                formcontent.appendChild(div_);
            }
        }
        else {
            alert("il n'y a que 4 véhicules disponibles!");
        }
    });
    div2.addEventListener('click', (e) => {
        e.preventDefault();
        if (contentValue.value === "Police")
            police.set_isRoadChosed(true);
        else
            fireman.set_isRoadChosed(true);
        formcontent.removeChild(div2);
        if (contentValue.value !== "Police")
            formcontent.appendChild(div_);
        if (fireman.get_islog() && contentValue.value === "Police" || (fireman.get_islog() && contentValue.value !== "Police")) {
            formcontent.appendChild(div3);
        }
        else {
            formcontent.appendChild(div4);
            if (formcontent.contains(div_))
                formcontent.removeChild(div_);
        }
    });
    div3.addEventListener('click', (e) => {
        e.preventDefault();
        police.set_isRoadValide(true);
        if (contentValue.value !== "Police") {
            fireman.set_isRoadValide(true);
            fireman.set_okayWithRoad(true);
            police.set_okayWithRoad(true);
        }
        formcontent.removeChild(div3);
        formcontent.appendChild(div4);
        if (formcontent.contains(div_))
            formcontent.removeChild(div_);
        // div4.addEventListener('click',(e:Event)=>{
        //   alert(police.get_nbCarsToSend()+" de "+ msg+ " ont été dispatcher!");
        //   if(contentValue.value==="Police")
        //     police.set_Dispatch(true);
        //   else{
        //     fireman.set_Dispatch(true);
        //   }
        //   if(contentValue.value==="Police")
        //     div4.innerHTML="<h3>Veillez attendre la confirmation des pompiers</h3>"
        //   console.log(police,fireman);
        // })
    });
    div4.addEventListener('click', (e) => {
        alert(police.get_nbCarsToSend() + " de " + msg + " ont été dispatcher!");
        if (contentValue.value === "Police") {
            police.set_Dispatch(true);
            police.set_isRoadValide(true);
        }
        else {
            fireman.set_Dispatch(true);
        }
        if (contentValue.value === "Police")
            div4.innerHTML = "<h3>Veillez attendre la confirmation des pompiers</h3>";
        console.log(police, fireman);
    });
    div_.addEventListener('click', () => {
        alert("oups! les pompiers ne sont pas d'accord avec la route!");
        formcontent.removeChild(div_);
        formcontent.appendChild(div4);
        if (formcontent.contains(div3))
            formcontent.removeChild(div3);
        fireman.set_isRoadValide(true);
    });
    // content.replaceWith(formcontent);
    content.appendChild(formcontent);
    console.log(content.children);
};
