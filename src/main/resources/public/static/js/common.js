export default {
    data() {
        return {
            timer: null,
            timeoutDuration: 30000 // 30秒
        };
    },
    mounted() {
        this.startTimeout();
    },
    methods: {
        startTimeout() {
            this.timer = setTimeout(() => {
                this.handleTimeout();
            }, this.timeoutDuration);
        },
        handleTimeout() {
            // 超时后的操作，例如跳转到登录页面
            // this.$router.push('/login');
            location.href = "./index.html";
        },
        resetTimeout() {
            clearTimeout(this.timer);
            this.startTimeout();
        }
    },
    beforeDestroy() {
        clearTimeout(this.timer);
    }
};