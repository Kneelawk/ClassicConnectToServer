# NilClassic

A simple template mod for modding classic using NilLoader.

## A quick note

NilLoader is not a general purpose loader intended to replace Fabric or Forge.
If you're here, you should either have a specific use case (e.g. wanting to
patch mods on old versions with broken tooling) or just want to mess around with
a new toy for the sake of it.

Additionally, NilLoader is currently *experimental* and ***API/ABI stability is
not yet guaranteed***.

If you're okay with that, then cool. Let's continue.

## Steps to use the template

1. You must pick a unique nilmod ID and put it in build.gradle. You will then
   have to rename src/main/resources/nilclassic.nilmod.css to use your ID.
2. You need to rename the package from com.kneelawk.nilclassic to a package
   you have permission to use; see [this wizard](https://unascribed.com/old/javapkg.html)
   for help if you don't know what a good package name is.
3. You will probably want to replace LICENSE with something else, unless CC0
   Public Domain Dedication is what you want.

## Building

The current version of NilGradle gets confused by classic mappings causing the mod to need to be built in 3 stages via
separate gradle invocations. These can be run via a bash command:

```bash
./gradlew clean && ./gradlew check && ./gradlew build
```

## Decompiling

Decompiling is broken on classic versions. Something about the class files just break VineFlower.

## How do I launch a development environment?

NilGradle does not currently (and likely never will) offer a dev environment. In
the future, NilLoader may gain the ability to be run a normal game in dev mode
and then be attached to an IDE for code hotswap. **You will have to restart the
game every time you make changes to your mod**.
