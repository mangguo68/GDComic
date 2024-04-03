plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.graduation.design"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.graduation.design"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    //dialogX
    implementation ("com.kongzue.dialogx:DialogX:0.0.49")
    //SmartRefreshLayout
    implementation("io.github.scwang90:refresh-layout-kernel:2.0.5")      //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:2.0.5")    //经典刷新头
    implementation("io.github.scwang90:refresh-header-radar:2.0.5")       //雷达刷新头
    implementation("io.github.scwang90:refresh-header-falsify:2.0.5")     //虚拟刷新头
    implementation("io.github.scwang90:refresh-header-material:2.0.5")    //谷歌刷新头
    implementation("io.github.scwang90:refresh-header-two-level:2.0.5")   //二级刷新头
    implementation("io.github.scwang90:refresh-footer-ball:2.0.5")        //球脉冲加载
    implementation("io.github.scwang90:refresh-footer-classics:2.0.5")    //经典加载
    //livedata scope
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    //glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    //banner
    implementation("io.github.youth5201314:banner:2.2.2")
    //qmui
    implementation("com.qmuiteam:qmui:2.0.1")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}