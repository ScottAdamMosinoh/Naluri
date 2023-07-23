# Naluri

<img width="486" alt="image" src="https://github.com/ScottAdamMosinoh/Naluri/assets/30062452/12050f70-6338-47f5-a021-24d7375d874e">


<B>Prerequisites</B>

-Android 7 emulator

-Android Studio

<B>The Concept</B>

Implementing pi value from https://pi.delivery/#introductionindex which bases their calculation on https://cloud.google.com/compute

While calculating circumference with formula of 2PiR

<B>Library Used</B>
1. Retrofit -> Retrofit helps with http calls
2. Koin -> koin was used to help inject viewmodels, this is highly recommended as it also ties lifecycle that eases developer's need in managing it ourself
3. ViewBinding -> reduces boiletplate when accessing view ids

<B>How to run </B>
1. Unzip package / clone repo
2. Open project with android studio
3. Ensure emulator is android 7 and above
4. Run application

<B> Future improvements </B>
1. Inject Retrofit into VM via KoinInjection
2. Coroutines -> implement coroutine with the sole purpose of better thread management for api calls
3. Add in visualiser for objects after calculating circumference
4. Independent views for each object (viewpager)

Any other suggestions are welcome

