console.log("login.js!!");

// 1, Vue.jsで扱うデータを用意する
const myData = {
    timer: null,
    start: Date.now(),
    timeoutDuration: 30000, // 30秒

    // アプリ名
    appName: "ログイン",
    pros: ["email"],

    isLogin: false,
    email: "",
    password: "",
    token:"",
}

// 2, Vue.jsの準備をする
const app = Vue.createApp({
    data(){
        return myData;// 扱うデータを指定する
    },
    created(){
        console.log("created!!");
        // Load
        // this.loadUsers();
    },
    mounted() {
        this.startTimeout();
    },
    methods:{
        loginHandle(){
            const headers = {
                'Content-Type': 'application/x-www-form-urlencoded',
            }

            const userInfo = {
                email: this.email,
                password: this.password,
            }

            // Axios
            const url = "/v1/login";
            axios.post(url, userInfo).then(res=>{
                const entries = Object.entries(res.data);
                let rtnLogin =  Object.fromEntries(entries);

                if(rtnLogin.result_code === 200){
                    this.token = rtnLogin.result.jwt;
                    this.isLoging = true;
                    setTimeout(()=>{
                        const object = {
                            value: this.token,
                            timestamp: new Date().getTime()
                        };
                        localStorage.setItem("tokenObj", JSON.stringify(object));
                        localStorage.setItem("userId", this.userInfo.email);
                        this.axios.defaults.headers.common['Authorization'] = this.token
                        this.isLogin = false;
                    },300);

                    localStorage.setItem("userId", this.email);

                    // 画面遷移
                    location.href = "./molecules/members.html";
                } else {
                    this.titleText = 'アカウントもしくはパスワードが違います。'
                }
            }).catch(err=>{
                console.log(err);
            });
        },

        setAuthFun() {
            let tokenObj = JSON.parse(localStorage.getItem("tokenObj"))
            let expireDays = 1000 * 60 * 60 * 24 * 15;
            if (tokenObj) {
                let dateString = tokenObj.timestamp
                let dtNow = new Date().getTime();
                if (dtNow - dateString > expireDays)
                {
                    localStorage.removeItem('tokenObj')
                    delete this.axios.defaults.headers.common['Authorization']
                }
                let token = tokenObj.value
                if (token) {
                    axios.defaults.headers.common['Authorization'] = token
                }
            }
        },

        // handleTime1() {
        //     this.timePara.currentTime = new Date().getTime();
        //     if (this.timePara.currentTime - this.timePara.lastTime > this.timePara.timeOut) {
        //         localStorage.clear();
        //         location.href = "/index.html";
        //     } else {
        //         this.timePara.lastTime = new Date().getTime();
        //     }
        // },

        //logout function
        logoutFun() {
            this.isLogout = true;
            this.userInfo = {}
            this.$store.commit('updateUserInfo', this.userInfo);

            localStorage.removeItem('tokenObj')
            localStorage.removeItem('tokenRefreshObj')
            localStorage.removeItem('authority')
            delete this.axios.defaults.headers.common['Authorization']
            setTimeout(()=>{
                this.$router.push('/login');
                this.isLogouting = false;
            },1)
        },
        // Set Session TimeOut
        startTimeout() {
            this.timer = setTimeout(() => {
                this.handleTimeout();
            }, this.timeoutDuration);
        },
        handleTimeout() {
            // 超时后的操作，例如跳转到登录页面
            // this.$router.push('/login');
            // location.href = "./index.html";
            this.logoutFun();
        },
        resetTimeout() {
            const end = Date.now();
            if (end - this.start > this.timeoutDuration) {
                this.logoutFun();
            } else {
                clearTimeout(this.timer);
                this.startTimeout();
            }
        },
        eventFunc() {
            window.addEventListener('mousemove', this.resetTimeout);
            window.addEventListener('keydown', this.resetTimeout);
        },
        destroyFunc() {
            window.removeEventListener('mousemove', this.resetTimeout);
            window.removeEventListener('keydown', this.resetTimeout);
        },
    },
    beforeDestroy() {
        clearTimeout(this.timer);
        this.destroyFunc();
    }
});
app.mount("#appLogin");// 3, Vue.jsを起動する