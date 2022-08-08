package com.cc.bootstrap.intl.demo.lambda;

import com.cc.bootstrap.intl.demo.lambda.vo.Album;
import com.cc.bootstrap.intl.demo.lambda.vo.Artist;
import com.cc.bootstrap.intl.demo.lambda.vo.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertEquals;

/**
 * 高级集合类和收集器
 */
public class Study5 {
    private static Logger LOGGER = LoggerFactory.getLogger(Study5.class);


    public static void main(String[] args) {
        Study5 study5 = new Study5();
        // 5.1 方法引用
        /**
         * Lambda 表达式经常调用参数。比 如想得到艺术家的姓名，Lambda 的表达式如下：
         * artist -> artist.getName() 这种用法如此普遍，因此 Java 8 为其提供了一个简写语法，叫作方法引用，帮助程序员重 用已有方法。
         * 用方法引用重写上面的 Lambda 表达式，代码如下： Artist::getName 标准语法为 Classname::methodName。
         * 需要注意的是，虽然这是一个方法，但不需要在后面 加括号，因为这里并不调用该方法。我们只是提供了和 Lambda 表达式等价的一种结构，
         * 在需要时才会调用。凡是使用 Lambda 表达式的地方，就可以使用方法引用。
         *
         * 读者只要记住，每次写出形如 x -> foo(x) 的 Lambda 表达式时，和直接调用方法 foo 是一样的。
         * 方法引用只不过是基于这样的事实，提供了一种简短的语 法而已
         *
         * 构造函数也有同样的缩写形式，如果你想使用 Lambda 表达式创建一个 Artist 对象，可能 会写出如下代码：
         * (name, nationality) -> new Artist(name, nationality) 使用方法引用，上述代码可写为： Artist::new
         *
         * 还可以用这种方式创建数组，下面的代码创建了一个字符串型的数组： String[]::new
         */

        // 5.2 元素顺序
        /**
         * 5.2.1 在一个有序集合中创建一个流时，流中的元素就按出现顺序排列
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream().collect(toList());
        assertEquals(numbers, sameOrder);
        /**
         * 5.2.2 如果集合本身就是无序的，由此生成的流也是无序的。
         */
        // 这会带来一些意想不到的结果，比如使用并行流时，forEach 方法不能保证元素是 按顺序处理的（第 6 章会详细讨论这些内容）。
        // 如果需要保证按顺序处理，应该使用 forEachOrdered 方法，它是你的朋友。


        /**
         * 5.3 使用收集器 收集器，一种通用的、从流生成复杂值的结构。只要将它传给 collect 方法，所有 的流就都可以使用它了。
         * 标准类库已经提供了一些有用的收集器，让我们先来看看。本章示例代码中的收集器都是从java.util.stream.Collectors
         * 类中静态导入的。
         */
        // 5.3.1 转换成其他集合
//        (1)调用 toList 或者 toSet方法时，不需要指定具体的类型。Stream 类库在背后自动为你 挑选出了合适的类型。
//        (2)本书后面会讲述如何使用 Stream 类库并行处理数据，收集并行操作 的结果需要的 Set，和对线程安全没有要求的 Set 类是完全不同的。
//        (3)可能还会有这样的情况，你希望使用一个特定的集合收集值，而且你可以稍后指定该集合 的类型。
//        比如，你可能希望使用 TreeSet，而不是由框架在背后自动为你指定一种类型的 Set。
//        此时就可以使用 toCollection，它接受一个函数作为参数，来创建集合（见例 5-5）。高级集合类和收集器
//        例 5-5 使用 toCollection，用定制的集合收集元素
//        stream.collect(toCollection(TreeSet::new));

        // 5.3.2 转换成值
        // (1) 还可以利用收集器让流生成一个值。maxBy 和 minBy 允许用户按某种特定的顺序生成一个 值。
        // 例 5-6 找出成员最多的乐队
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("she", "selina,hebe,ala", "chinese"));
        artists.add(new Artist("esrf", "xx,23ac", "uk"));
        artists.add(new Artist("q3d", "cx,fafe,veq", "us"));
        artists.add(new Artist("fae", "da,f,saf", "karea"));
        Optional<Artist> optionalArtist = study5.biggestGroup(artists.stream());
        LOGGER.info("optionalArtist [{}]", optionalArtist);
        // 这样写有个问题就是，同时都是最大的，其他数据未返回，所以最好这个maxBy是查询没有重复值的功能。
        // (2) 还有些收集器实现了一些常用的数值运算。
        List<Album> albums = new ArrayList<>();

        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("新年好呀", 1));
        tracks.add(new Track("小燕子", 2));
        List<Artist> musicians = new ArrayList<>();
        musicians.add(new Artist("q3d", "cx,fafe,veq", "us"));
        albums.add(new Album("as", tracks, musicians));

        List<Track> tracks2 = new ArrayList<>();
        tracks2.add(new Track("爸爸妈妈", 1));
        tracks2.add(new Track("中国红", 2));
        tracks2.add(new Track("My Heart will go on", 3));
        tracks2.add(new Track("always happy", 4));
        List<Artist> musicians2 = new ArrayList<>();
        musicians2.add(new Artist("she", "selina,hebe,ala", "chinese"));
        musicians2.add(new Artist("daf", "sdf,99s", "sdf"));

        albums.add(new Album("我问问", tracks2, musicians2));

        double averageTrackSize = study5.averageNumberOfTracks(albums);
        LOGGER.info("averageTrackSize [{}]", averageTrackSize);

        // 5.3.3 数据分块 另外一个常用的流操作是将其分解成两个集合。
        /**
         * 假设有一个艺术家组成的流，你可能希望 将其分成两个部分，一部分是独唱歌手，另一部分是由多人组成的乐队。
         * 可以使用两次过 滤操作，分别过滤出上述两种艺术家
         *
         * 但是这样操作起来有问题。首先，为了执行两次过滤操作，需要有两个流。其次，如果过 滤操作复杂，每个流上都要执行这样的操作，代码也会变得冗余。
         * 幸好我们有这样一个收集器 partitioningBy，它接受一个流，并将其分成两部分.
         * 它使用 Predicate 对象判断一个元素应该属于哪个部分，并根据布尔值返回一 个 Map 到列表。因此，对于 true List 中的元素，
         * Predicate 返回 true；对其他 List 中的 元素，Predicate 返回 false。
         */
        List<Artist> artists_partition = new ArrayList<>();
        artists_partition.add(new Artist("she", "selina,hebe,ala", "chinese"));
        artists_partition.add(new Artist("esrf", "xx", "uk"));
        artists_partition.add(new Artist("q3d", "cx,fafe,veq", "us"));
        artists_partition.add(new Artist("fae", "da", "karea"));
        Map<Boolean, List<Artist>> partitionArtistMap = study5.bandsAndSoloRef(artists_partition.stream());
        LOGGER.info("partitionArtistMap [{}]", partitionArtistMap);

        // 转为Map<String, String>格式
        Map<String, String> nameUuidMap = artists_partition.stream()
                .collect(Collectors.toMap(Artist::getName, Artist::getOrigin, (p1, p2) -> p1));

        // 5.3.5 数据分组 数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，可以使 用任意值对数据分组。
        Map<Artist, List<Album>> goupby_mainsinger = study5.albumsByArtist(albums.stream());
        LOGGER.info("goupby_mainsinger [{}]", goupby_mainsinger);

        // 5.3.6 字符串 很多时候，收集流中的数据都是为了在最后生成一个字符串。
        //例 5-12 使用流和收集器格式化艺术家姓名
        String result = artists.stream().map(Artist::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        LOGGER.info("result [{}]", result);

        // 5.3.7 组合收集器 虽然读者现在看到的各种收集器已经很强大了，但如果将它们组合起来，会变得更强大。
        Map<Artist, Long> albumsSize_mainSinger = study5.numberOfAlbums(albums.stream());
        LOGGER.info("albumsSize_mainSinger [{}]", albumsSize_mainSinger);

        // 5.3.8 重构和定制收集器 JDK 提供的收集器没有什么特别的，完全可以定制自己的收集器，而且定制起来相当 简单
        /**
         * 读者可能还没忘记在例 5-11 中，如何使用 Java 7 连接字符串，尽管形式并不优雅。让我们 逐步重构这段代码，
         * 最终用合适的收集器实现原有代码功能。在工作中没有必要这样做， JDK 已经提供了一个完美的收集器 joining。
         * 这里只是为了展示如何定制收集器，以及如 何使用 Java 8 提供的新功能来重构遗留代码。
         */
        //例 5-17 使用 for 循环和 StringBuilder 格式化艺术家姓名
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1) builder.append(", ");
            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        String resultStr = builder.toString();
        LOGGER.info("resultStr [{}]", resultStr);

        // 显然，可以使用 map 操作，将包含艺术家的流映射为包含艺术家姓名的流。例 5-18 展示了 使用了流的 map 操作重构后的代码。
        //例 5-18 使用 forEach 和 StringBuilder 格式化艺术家姓名
        StringBuilder builder1 = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (builder1.length() > 1) builder1.append(", ");
                    builder1.append(name);
                });
        builder1.append("]");
        String resultStr1 = builder1.toString();
        LOGGER.info("resultStr1 [{}]", resultStr1);
        // 后续步骤见书籍

        // 5.3.9 对收集器的归一化处理
        //5.4 一些细节
        /**
         * Lambda 表达式的引入也推动了一些新方法被加入集合类。让我们来看看 Map 类的一些变 化。构建 Map 时，为给定值计算键值是常用的操作之一，
         * 一个经典的例子就是实现一个缓存。 传统的处理方式是先试着从 Map 中取值，如果没有取到，创建一个新值并返回。
         *
         * (1)Java 8 引入了一个新方法 computeIfAbsent，该方法接受一个 Lambda 表达式，值不存在时 使用该 Lambda 表达式计算新值。
         * 例 5-32 使用 computeIfAbsent 缓存
         * public Artist getArtist(String name) {
         * return artistCache.computeIfAbsent(name, this::readArtistFromDB); }
         * 你可能还希望在值不存在时不计算，为 Map 接口新增的 compute 和 computeIfAbsent 就能处 理这些情况。
         *
         * (2)在工作中，你可能尝试过在 Map 上迭代。过去的做法是使用 value 方法返回一个值的集合， 然后在集合上迭代。这样的代码不易读。
         * 例 5-33 展示了本章早些时候介绍的一种方式，创 建一个 Map，然后统计每个艺术家专辑的数量。
         * 例 5-33 一种丑陋的迭代 Map 的方式
         * Map<Artist, Integer> countOfAlbums = new HashMap<>();
         * for(Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
         *   Artist artist = entry.getKey();
         *   List<Album> albums = entry.getValue();
         *   countOfAlbums.put(artist, albums.size());
         * }
         * 谢天谢地，Java 8 为 Map 接口新增了一个 forEach 方法，该方法接受一个 BiConsumer 对象 为参数（该对象接受两个参数，返回空），
         * 通过内部迭代编写出易于阅读的代码，关于内 部迭代请参考 3.1 节。使用该方法重写后的代码如例 5-34 所示。
         * 例 5-34 使用内部迭代遍历 Map 里的值
         * Map<Artist, Integer> countOfAlbums = new HashMap<>();
         * albumsByArtist.forEach((artist, albums) -> { countOfAlbums.put(artist, albums.size()); });
         */

    }

    //例 5-6 找出成员最多的乐队
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist,Long> getCount = artist -> artist.memberCount();
        return artists.collect(maxBy(comparing(getCount)));
    }

    //5-7 找出一组专辑上曲目的平均数
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream().collect(averagingInt(album -> album.getTracks().size()));
    }

    //例 5-9 使用方法引用将艺术家组成的 Stream 分成乐队和独唱歌手两部分
    public Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }

    //例 5-10 使用主唱对专辑分组
    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMusicians().get(0)));
    }

    //例 5-14 使用收集器计算每个主唱艺术家的专辑数（groupby后再计数）
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMusicians().get(0), counting()));
    }

    // 根据名字分组，分组数据多个
    public Map<String, List<Album>> groupByName(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getName));
    }

    // 将转成Map，数据只有一个
    public Map<String, Album> convertToMap(Stream<Album> albums) {
        return albums.collect(toMap(Album::getName, album -> album));
    }
}
