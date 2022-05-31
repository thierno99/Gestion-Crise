import { CreatElt, RenderActions } from "./commons/common.js";
import FireMan from "./FireMan.js";
import Police from "./Police.js";
window.onload = () => {
    const action = document.querySelector('.action');
    const title = document.querySelector('.content-title');
    const content = document.querySelector('.content');
    const contentPolice = document.querySelector('.police');
    const contentfireman = document.querySelector('.pompier');
    const actionsTodo = document.querySelector('.actionsTodo'); //ul des actions  
    let policeMan = new Police();
    let fireMan = new FireMan();
    const msg = (name) => {
        const success = CreatElt('div');
        success.classList.add('success');
        success.innerHTML = name + " connecté avec sussès!";
        content === null || content === void 0 ? void 0 : content.appendChild(success);
    };
    let contentValue = new Object();
    contentPolice === null || contentPolice === void 0 ? void 0 : contentPolice.addEventListener('click', (e) => {
        Object.assign(contentValue, { title: "Police Man Logged" });
        Object.assign(contentValue, { value: "Police" });
        if (actionsTodo && content) {
            content.innerHTML = "";
            actionsTodo.innerHTML = "";
            if (actionsTodo.children.length <= 0) {
                RenderActions(policeMan, fireMan, actionsTodo, content, contentValue, 'pm');
                const pm = document.querySelector(".pm");
                pm === null || pm === void 0 ? void 0 : pm.addEventListener('click', () => {
                    policeMan.set_islog(true);
                    if (policeMan.get_islog()) {
                        pm === null || pm === void 0 ? void 0 : pm.setAttribute('disabled', 'true');
                        if (action === null || action === void 0 ? void 0 : action.classList.contains('hide')) {
                            action.classList.remove('hide');
                        }
                        if (title && content) {
                            //HandleContent(policeMan,fireMan,title,content,contentValue);
                            msg(policeMan.get_Name());
                        }
                    }
                });
            }
        }
    });
    contentfireman === null || contentfireman === void 0 ? void 0 : contentfireman.addEventListener('click', (e) => {
        Object.assign(contentValue, { title: "Fire Man Logged" });
        Object.assign(contentValue, { value: "Pompier" });
        if (actionsTodo && content) {
            content.innerHTML = "";
            actionsTodo.innerHTML = "";
            if (actionsTodo.children.length <= 0) {
                RenderActions(policeMan, fireMan, actionsTodo, content, contentValue, 'fm');
                const fm = document.querySelector(".fm");
                fm === null || fm === void 0 ? void 0 : fm.addEventListener('click', () => {
                    fireMan.set_islog(true);
                    fireMan.set_islog(true);
                    if (fireMan.get_islog()) {
                        fm === null || fm === void 0 ? void 0 : fm.setAttribute('disabled', 'true');
                        if (action === null || action === void 0 ? void 0 : action.classList.contains('hide')) {
                            action.classList.remove('hide');
                        }
                        if (title && content)
                            // HandleContent(policeMan,fireMan, title,content,contentValue);
                            msg(fireMan.get_Name());
                    }
                });
            }
        }
    });
};
