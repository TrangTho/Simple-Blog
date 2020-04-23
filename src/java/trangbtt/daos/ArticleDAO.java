/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trangbtt.dbs.MyConnection;
import trangbtt.dtos.ArticleDTO;

/**
 *
 * @author trang
 */
public class ArticleDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public ArticleDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (conn != null) {
            conn.close();
        }

    }
    
    public int countSearchTotalPage(String searchValue) throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where title like ? and status = 'Active'";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int countSearchTotalPageAdminContent(String content) throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where ContentArticle like ?";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + content + "%");
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int countSearchTotalPageAdmin(String tile, String status) throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where title like ? and status = ?";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + tile + "%");
            pre.setString(2, status);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int countSearchTotalPageAdminAll(String tile) throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where title like ?";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + tile + "%");
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int countTotalPage() throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where status = 'Active'";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public List<ArticleDTO> searchActicle(String searchValue, int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String title = null;
        String shortDescription = null;
        String author = null;
        String date = null;
        try {

            String sql = "Select a.ID, a.title, a.Descriptions, a.author, a.PostingDate "
                    + "from Blog as a where a.title like ? and a.status = 'Active' "
                    + "order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            pre.setInt(2, (currentPage - 1) * next);
            pre.setInt(3, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                shortDescription = rs.getString("Descriptions");
                author = rs.getString("Author");
                date = rs.getString("PostingDate");
                dto = new ArticleDTO(id, title, shortDescription, author, date);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> searchActicleByContent(String searchValue, int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String title = null;
        String shortDescription = null;
        String author = null;
        String date = null;
        String content = null;
        String status = null;
        try {

            String sql = "Select a.ID, a.title, a.Descriptions,a.ContentArticle, a.author, a.PostingDate, a.Status from Blog as a where a.ContentArticle like ?  order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            pre.setInt(2, (currentPage - 1) * next);
            pre.setInt(3, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                shortDescription = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                date = rs.getString("PostingDate");
                status = rs.getString("Status");
                dto = new ArticleDTO(id, title, shortDescription, content, author, date, status);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> searchActicleByTitleStatus(String title, String status, int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String shortDescription = null;
        String author = null;
        String date = null;
        String content = null;
        try {

            String sql = "Select a.ID, a.title, a.Descriptions,a.ContentArticle, a.author, a.PostingDate, a.Status from Blog as a where  a.Title like ? and a.Status = ? order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            pre.setString(2, status);
            pre.setInt(3, (currentPage - 1) * next);
            pre.setInt(4, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                shortDescription = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                date = rs.getString("PostingDate");
                status = rs.getString("Status");
                dto = new ArticleDTO(id, title, shortDescription, content, author, date, status);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> searchActicleByTitleStatusAll(String title, int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String shortDescription = null;
        String author = null;
        String date = null;
        String content = null;
        String status = null;
        try {

            String sql = "Select a.ID, a.title, a.Descriptions,a.ContentArticle, a.author, a.PostingDate, a.Status from Blog as a where  a.Title like ? order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            pre.setInt(2, (currentPage - 1) * next);
            pre.setInt(3, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                shortDescription = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                date = rs.getString("PostingDate");
                status = rs.getString("Status");
                dto = new ArticleDTO(id, title, shortDescription, content, author, date, status);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertNewBlog(String title, String description, String content, String author) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Blog(Title, Descriptions, ContentArticle, Author, Status ) values(?,?,?,?,'New')";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, title);
            pre.setString(2, description);
            pre.setString(3, content);
            pre.setString(4, author);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateDelete(String  user) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Blog set Status = 'Delete' Where Author like ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,"%"+ user+"%");
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateApproval(int id) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Blog set Status = 'Active' Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public List<ArticleDTO> loadListTitle() throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        String title = null;

        try {
            String sql = "Select Title From Blog";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                title = rs.getString("Title");
                dto = new ArticleDTO(title);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> loadTopTitle() throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
         int id = 0;
        String title = null;
        String description = null;
        String author = null;
        String postingDate = null;
        try {
            String sql = "SELECT TOP(4) a.ID, a.title, a.Descriptions, a.author, a.PostingDate from Blog as a ORDER BY PostingDate DESC";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                description = rs.getString("Descriptions");
                author = rs.getString("Author");
                postingDate = rs.getString("PostingDate");
                dto = new ArticleDTO(id, title, description, author, postingDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> loadListArticle(int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String title = null;
        String description = null;
        String author = null;
        String postingDate = null;

        try {
            String sql = "Select a.ID, a.title, a.Descriptions, a.author, a.PostingDate from Blog as a where a.status = 'Active' order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, (currentPage - 1) * next);
            pre.setInt(2, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                description = rs.getString("Descriptions");
                author = rs.getString("Author");
                postingDate = rs.getString("PostingDate");
                dto = new ArticleDTO(id, title, description, author, postingDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> loadListArticleDetail(int currentPage, int next) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        int id = 0;
        String title = null;
        String description = null;
        String content = null;
        String status = null;
        String author = null;
        String postingDate = null;

        try {
            String sql = "Select a.ID, a.title, a.Descriptions, a.ContentArticle, a.author, a.PostingDate, a.Status "
                    + "from Blog as a where a.status = 'Active' or a.Status = 'New' or a.Status = 'Delete' "
                    + "order by a.PostingDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, (currentPage - 1) * next);
            pre.setInt(2, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                title = rs.getString("Title");
                description = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                postingDate = rs.getString("PostingDate");
                status = rs.getString("Status");
                dto = new ArticleDTO(id, title, description, content, author, title, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> findByLikeIDBlog(String id) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        String title = null;
        String description = null;
        String content = null;
        String author = null;
        String postingDate = null;
        try {
            String sql = "Select Title, Descriptions, ContentArticle, Author, PostingDate From Blog Where ID = ? and Status = 'Active'";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                title = rs.getString("Title");
                description = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                postingDate = rs.getString("PostingDate");
                dto = new ArticleDTO(title, description, content, author, title);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> findByLikeIDNeStatus(String id) throws Exception {
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        String title = null;
        String description = null;
        String content = null;
        String author = null;
        String postingDate = null;
        String status = null;
        int idP = Integer.parseInt(id);
        try {
            String sql = "Select Title, Descriptions, ContentArticle, Author, PostingDate, Status From Blog Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, idP);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                title = rs.getString("Title");
                description = rs.getString("Descriptions");
                content = rs.getString("ContentArticle");
                author = rs.getString("Author");
                postingDate = rs.getString("PostingDate");
                status = rs.getString("Status");
                dto = new ArticleDTO(idP, title, description, content, author, postingDate, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countRow() throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where status = 'Active'";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countRowDetail() throws Exception {
        int result = 0;
        try {
            String sql = "select count(ID) as total from Blog where status = 'Active' or status = 'New'";
            conn = MyConnection.getMyConnection();

            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
