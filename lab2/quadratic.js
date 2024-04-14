class Quadratic {
    #A;
    #B;
    #C;
    #delta;
    #x1;
    #x2;

    constructor(a, b, c) {
        this.#A = a;
        this.#B = b;
        this.#C = c;
        this.#delta = null;
        this.#x1 = null;
        this.#x2 = null;
    }

    calcDelta() {
        this.#delta = (this.#B * this.#B) - 4 * this.#A * this.#C;
        return this.#delta;
    }

    zeroPlaces() {
        let d = this.calcDelta();
        if (d > 0) {
            this.#x1 = ((-this.#B) - Math.sqrt(d)) / (2 * this.#A);
            this.#x2 = ((-this.#B) + Math.sqrt(d)) / (2 * this.#A);
        } else if (d === 0) {
            this.#x1 = (-this.#B) / (2 * this.#A);
        } else {
            this.#x1 = null;
            this.#x2 = null;
        }
    }

    getX1() {
        return this.#x1;
    }

    getX2() {
        return this.#x2;
    }

    getDelta() {
        return this.#delta;
    }

    validate() {
        if (isNaN(this.#A) || isNaN(this.#B) || isNaN(this.#C)) {
            return false;
        }
        return true;
    }

    calculate() {
        if (this.validate()) {
            this.zeroPlaces();
        } else {
            console.error('Invalid data provided');
        }
    }
}

function hook() {
    let element = document.getElementById('calculate');
    element.addEventListener('click', function (event) {
        let a = parseFloat(document.getElementById('coeff_a').value);
        let b = parseFloat(document.forms['data'].elements['cb'].value);
        let c = parseFloat(document.getElementById('coeff_c').value);

        let equation = new Quadratic(a, b, c);
        equation.calculate();

        if (equation.getDelta() === null) {
            return false;
        }
        let list = document.getElementById('list');
        let x1 = null;
        let x2 = null;

        if (list.hasChildNodes()) {
            while (list.firstChild) {
                list.removeChild(list.firstChild);
            }
        }
        let result = document.createTextNode('Result');
        if (equation.getDelta() > 0) {
            x1 = 'x₁=' + equation.getX1();
            x2 = 'x₂=' + equation.getX2();
        } else if (equation.getDelta() === 0) {
            x1 = 'x=' + equation.getX1();
        } else {
            x1 = 'No zero places, Δ < 0';
        }
        let ul = document.createElement('ul');
        let li = document.createElement('li');
        li.appendChild(document.createTextNode(x1));
        ul.appendChild(li);
        list.appendChild(result);
        list.appendChild(ul);
        if (x2) {
            let li2 = document.createElement('li');
            li2.appendChild(document.createTextNode(x2));
            ul.appendChild(li2);
        }
    });
}
