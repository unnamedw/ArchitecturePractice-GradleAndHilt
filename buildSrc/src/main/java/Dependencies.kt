import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    //project level
    const val gradle = "8.1.0"
    const val kotlin = "1.8.0"
    const val ksp = "1.8.10-1.0.9"

    //libs
    val coreKtx = "1.9.0"
    val appcompat = "1.6.1"
    val material = "1.9.0"
    val constraintLayout = "2.1.4"
    val hilt = "2.44"
    val retrofit = "2.9.0"
    val moshi = "1.14.0"
    val glide = "4.16.0"

    //test
    val junit = "4.13.2"
    val extJunit = "1.1.5"
    val espresso = "3.5.1"
}

object AppDependencies {

    //libs
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    private val converterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    private val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    private val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //android test libs
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"

    //ksp
    private val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    private val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(material)
        add(constraintLayout)
        add(hiltAndroid)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val ksps = arrayListOf<String>().apply {
        add(hiltAndroidCompiler)
        add(moshiKotlinCodegen)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.ksp(list: List<String>) {
    list.forEach { dependency ->
        add("ksp", dependency)
    }
}