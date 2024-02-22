package ncnk.make.backendroadmap.domain.repository.SubCategory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ncnk.make.backendroadmap.domain.entity.QSubCategory;
import ncnk.make.backendroadmap.domain.entity.SubCategory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubCategoryRepositoryImpl implements SubCategoryCustomRepository {
    private final JPAQueryFactory queryFactory;
    @PersistenceContext
    EntityManager em;

    @Override
    public List<SubCategory> addLikeCount(SubCategory subCategory) {
        queryFactory.update(QSubCategory.subCategory)
                .set(QSubCategory.subCategory.likeCount, QSubCategory.subCategory.likeCount.add(1))
                .where(QSubCategory.subCategory.eq(subCategory))
                .execute();

        return queryFactory
                .selectFrom(QSubCategory.subCategory)
                .fetch();
    }

    @Override
    public List<SubCategory> subLikeCount(SubCategory subCategory) {
        queryFactory.update(QSubCategory.subCategory)
                .set(QSubCategory.subCategory.likeCount, QSubCategory.subCategory.likeCount.subtract(1))
                .where(QSubCategory.subCategory.eq(subCategory))
                .execute();

        em.flush();
        em.clear();

        return queryFactory
                .select(QSubCategory.subCategory)
                .fetch();
    }
}
