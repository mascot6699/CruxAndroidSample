class RNLocalStorage extends window.CruxPay.storage.StorageService {
    constructor() {
        super(...arguments);
        this.setItem = async (key, value) => setItem(key, value);
        this.getItem = async (key) => getItem(key);
    }
}
const s = new RNLocalStorage();