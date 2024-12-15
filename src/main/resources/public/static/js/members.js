console.log("members.js!!");

// 1, Vue.jsで扱うデータを用意する
const myData = {
    timer: null,
    start: Date.now(),
    timeoutDuration: 30000, // 30秒

    appName: "ユーザーリスト",// アプリ名
    // addresses: {},
    pros: ["user_id"],
    users: [],
    target_user: {
        user_id: "",
        name_last: "",
        name_first: "",
        user_role: ""
    },
    user_id: "",
    name_last: "",
    name_first: "",
    user_role: "",

    timePara:{
        lastTime: null,
        currentTime: null,
        timeOut: 3 * 60 * 1000,
    }
}

// 2, Vue.jsの準備をする
const app = Vue.createApp({
    data(){
        return myData;// 扱うデータを指定する
    },
    created(){
        this.startTimeout();
        console.log("created!!");
        this.loadUsers();// Load
    },
    mounted() {
        this.eventFunc();
    },
    methods:{
        loadUsers(){
            const  userId = localStorage.getItem("userId");
            alert("members->userId: "+userId);
            console.log("load!!");
            // Axios
            // const url = URL;
            axios.get("/v1/users").then(res=>{
                //console.log(res.data);
                const entries = Object.entries(res.data);
                entries.sort((a, b)=>{return a[0] < b[0];});
                this.users = Object.fromEntries(entries);

            }).catch(err=>{
                console.log(err);
            });
        },
        showDetail(user_id){
            // localStorage.setItem("user_id",user_id);
            // localStorage.setItem("userId",user_id);
            location.href = "./member.html";
        },

        updateUser(user_id){
            console.log("save!!");
            if(user_id == null) {
                alert("ユーザーが存在しない。");
                return;
            }
            // ID
            // Target User
            const upUser = {
                "user_id": this.user_id,
                "name_last": this.name_last,
                "name_first": this.name_first,
                "user_role": this.user_role
            };
            // Axios
            const url = "/v1/user";
            axios.put(url, upUser).then(res=>{
                //console.log(res.data);
                this.loadUsers();// Load
            }).catch(err=>{
                console.log(err);
            });
        },
        deleteUser(idx){
            console.log("delete!!");
            // Axios
            this.target_user = this.users[idx];
            const url = "/v1/user?user_id="+this.target_user.user_id;
            axios.delete(url).then(res=>{
                //console.log(res.data);
                this.loadUsers();// Load
            }).catch(err=>{
                console.log(err);
            });
        },
        showEditModal(idx){
            this.target_user = this.users[idx];
            console.log("showEditModal:" + this.target_user);
            if(this.target_user.user_id == null){
                this.target_user.user_id = "";
                this.target_user.name_last = "";
                this.target_user.name_first = "";
                this.target_user.user_role = "";
            }
        },
        showDeleteModal(idx){
            this.target_user = this.users[idx];
            console.log("showDeleteModal:" + this.target_user);
        },
        logoutFun(){
            localStorage.clear();
            location.href = "/index.html";
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
app.mount("#appMembers");// 3, Vue.jsを起動する